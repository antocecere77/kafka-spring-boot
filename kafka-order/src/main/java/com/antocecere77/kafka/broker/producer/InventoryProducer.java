package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.InventoryMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryProducer {

    private final KafkaTemplate<String, InventoryMessage> kafkaTemplate;

    public void publish(InventoryMessage message) {
        kafkaTemplate.send("t.commodity.inventory", message.getItem(), message);
    }

}
