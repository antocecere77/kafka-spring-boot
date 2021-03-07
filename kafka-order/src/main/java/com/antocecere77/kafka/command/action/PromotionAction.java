package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.PromotionRequest;
import com.antocecere77.kafka.broker.message.PromotionMessage;
import com.antocecere77.kafka.broker.producer.PromotionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromotionAction {

    private final PromotionProducer producer;

    public void publishToKafka(PromotionRequest request) {

        var message = PromotionMessage.builder()
                .promotionCode(request.getPromotionCode())
                .build();

        producer.publish(message);
    }
}
