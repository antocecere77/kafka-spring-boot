package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.InventoryRequest;
import com.antocecere77.kafka.broker.message.InventoryMessage;
import com.antocecere77.kafka.broker.producer.InventoryProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryAction {

    private final InventoryProducer producer;

    public void publishToKafka(InventoryRequest request, String type) {
        var message = new InventoryMessage();

        message.setLocation(request.getLocation());
        message.setItem(request.getItem());
        message.setQuantity(request.getQuantity());
        message.setType(type);
        message.setTransactionTime(request.getTransactionTime());

        producer.publish(message);
    }

}