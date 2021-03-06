package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.OrderMessage;
import com.antocecere77.kafka.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public void publish(OrderMessage message) {
        kafkaTemplate.send("t.commodity.order", message.getOrderNumber(), message);
    }
}
