package com.antocecere77.kafka.broker.consumer;

import com.antocecere77.kafka.broker.message.DiscountMessage;
import com.antocecere77.kafka.broker.message.PromotionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(topics = "t.commodity.promotion")
public class PromotionListener {

    @KafkaHandler
    public void listenerPromotion(PromotionMessage message) {
        log.info("Processing promotion: {}", message);
    }

    @KafkaHandler
    public void listenerDiscount(DiscountMessage message) {
        log.info("Processing discount: {}", message);
    }

}
