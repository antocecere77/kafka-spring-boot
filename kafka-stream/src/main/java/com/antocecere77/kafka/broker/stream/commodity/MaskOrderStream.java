package com.antocecere77.kafka.broker.stream.commodity;

import com.antocecere77.kafka.broker.message.OrderMessage;
import com.antocecere77.kafka.util.CommodityStreamUtils;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
public class MaskOrderStream {

    @Bean
    public KStream<String, OrderMessage> kStreamCommodityTrading(StreamsBuilder builder) {

        var stringSerde = Serdes.String();
        var orderSerde = new JsonSerde<OrderMessage>(OrderMessage.class);

        KStream<String, OrderMessage> maskedOrderStream = builder
                .stream("t.commodity.order",
                Consumed.with(stringSerde, orderSerde))
                .mapValues(CommodityStreamUtils::maskCreditCard);

        maskedOrderStream.to("t.commodity.order-masked", Produced.with(stringSerde, orderSerde));

        //useful for debugging, but better not use it opn production
        maskedOrderStream.print(Printed.<String, OrderMessage>toSysOut().withLabel("Masked Order Stream"));

        return maskedOrderStream;
    }
}











