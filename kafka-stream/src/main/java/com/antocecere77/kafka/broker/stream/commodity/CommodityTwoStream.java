package com.antocecere77.kafka.broker.stream.commodity;

import com.antocecere77.kafka.broker.message.OrderMessage;
import com.antocecere77.kafka.broker.message.OrderPatternMessage;
import com.antocecere77.kafka.broker.message.OrderRewardMessage;
import com.antocecere77.kafka.util.CommodityStreamUtil;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import static com.antocecere77.kafka.util.CommodityStreamUtil.*;

@Configuration
public class CommodityTwoStream {

    @Bean
    public KStream<String, OrderMessage> kStreamCommodityTrading(StreamsBuilder builder) {

        var stringSerde = Serdes.String();
        var orderSerde = new JsonSerde<OrderMessage>(OrderMessage.class);
        var orderPatternSerde = new JsonSerde<>(OrderPatternMessage.class);
        var orderRewardSerde = new JsonSerde<>(OrderRewardMessage.class);

        KStream<String, OrderMessage> maskedOrderStream = builder.stream("t.commodity.order",
                Consumed.with(stringSerde, orderSerde))
                .mapValues(CommodityStreamUtil::maskCreditCard);

        // 1st sink stream to pattern
        // summarize order item (total) = price * quantity
        @SuppressWarnings("unchecked")
        KStream<String, OrderPatternMessage>[] patternStream = maskedOrderStream
                .mapValues(CommodityStreamUtil::mapToOrderPattern).branch(isPlastic(), (k, v) -> true);

        int plasticIndex = 0;
        int noPlasticIndex = 1;

        patternStream[plasticIndex].to("t.commodity.pattern-two.plastic", Produced.with(stringSerde, orderPatternSerde));
        patternStream[noPlasticIndex].to("t.commodity.pattern-two.notplastic", Produced.with(stringSerde, orderPatternSerde));

        // 2nd sink stream reward
        // filter only "large" quantity
        KStream<String, OrderRewardMessage> rewardStream = maskedOrderStream.filter(isLargeQuantity())
                .filterNot(isCheap())
                .mapValues(CommodityStreamUtil::mapToOrderReward);
        rewardStream.to("t.commodity.reward-two", Produced.with(stringSerde, orderRewardSerde));

        // 3rd sink stream to storage
        // no transformation
        KStream<String, OrderMessage> storageStream = maskedOrderStream.selectKey(generateStorageKey());
        storageStream.to("t.commodity.storage-two", Produced.with(stringSerde, orderSerde));

        return maskedOrderStream;
    }
}










