package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.PromotionMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionProducer {

    private final KafkaTemplate<String, PromotionMessage> kafkaTemplate;

    public void publish(PromotionMessage promotionMessage) {
        try {
            var sendResult = kafkaTemplate.send("t.commodity.promotion", promotionMessage).get();
            log.info("Send result success for message {}", sendResult.getProducerRecord().value());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error publishing {}, cause {}", promotionMessage, e.getMessage());
        }
    }
}
