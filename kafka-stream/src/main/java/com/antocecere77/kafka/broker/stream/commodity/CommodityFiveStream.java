package com.antocecere77.kafka.broker.stream.commodity;

import com.antocecere77.kafka.broker.message.OrderMessage;
import com.antocecere77.kafka.broker.message.OrderPatternMessage;
import com.antocecere77.kafka.broker.message.OrderRewardMessage;
import com.antocecere77.kafka.util.CommodityStreamUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaStreamBrancher;
import org.springframework.kafka.support.serializer.JsonSerde;

import static com.antocecere77.kafka.util.CommodityStreamUtil.*;

@Slf4j
@Configuration
public class CommodityFiveStream {

    @Bean
    public KStream<String, OrderMessage> kstreamCommodityTrading(StreamsBuilder builder) {
        var stringSerde = Serdes.String();
        var orderSerde = new JsonSerde<>(OrderMessage.class);
        var orderPatternSerde = new JsonSerde<>(OrderPatternMessage.class);
        var orderRewardSerde = new JsonSerde<>(OrderRewardMessage.class);

        KStream<String, OrderMessage> maskedOrderStream = builder
                .stream("t.commodity.order", Consumed.with(stringSerde, orderSerde))
                .mapValues(CommodityStreamUtil::maskCreditCard);

        // 1st sink stream to pattern
        final var branchProducer = Produced.with(stringSerde, orderPatternSerde);
        new KafkaStreamBrancher<String, OrderPatternMessage>()
                .branch(isPlastic(), kstream -> kstream.to("t.commodity.pattern-five.plastic", branchProducer))
                .defaultBranch(kstream -> kstream.to("t.commodity.pattern-five.notplastic", branchProducer))
                .onTopOf(maskedOrderStream.mapValues(CommodityStreamUtil::mapToOrderPattern));

        // 2nd sink stream to reward
        KStream<String, OrderRewardMessage> rewardStream = maskedOrderStream.filter(isLargeQuantity())
                .filterNot(isCheap()).map(mapToOrderRewardChangeKey());
        rewardStream.to("t.commodity.reward-five", Produced.with(stringSerde, orderRewardSerde));

        // 3rd sink stream to storage
        KStream<String, OrderMessage> storageStream = maskedOrderStream.selectKey(generateStorageKey());
        storageStream.to("t.commodity.storage-five", Produced.with(stringSerde, orderSerde));

        // 4th stream for fraud
        maskedOrderStream.filter((k,v) -> v.getOrderLocation().toUpperCase().startsWith("C"))
                .foreach((k,v) -> this.reportFraud(v));

        return maskedOrderStream;
    }

    private void reportFraud(OrderMessage message) {
        log.info("Reporting fraud {}", message);
    }
}
