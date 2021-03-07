package com.antocecere77.kafka.broker.consumer;

import com.antocecere77.kafka.broker.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderListener {

    @KafkaListener(topics = "t.commodity.order")
    public void listen(OrderMessage message) {

        var totalItemAmount = message.getPrice() * message.getQuantity();
        log.info("Processing order {}, item {}, credit card number {}. total amount for this item is {} ",
                message.getOrderNumber(), message.getItemName(), message.getCreditCardNumber(), totalItemAmount);

    }
}
