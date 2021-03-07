package com.antocecere77.kafka.broker.consumer;

import com.antocecere77.kafka.broker.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderListener {

    @KafkaListener(topics = "t.commodity.order")
    public void listen(ConsumerRecord<String, OrderMessage> consumerRecord) {
        var headers = consumerRecord.headers();
        var orderMessage = consumerRecord.value();

        log.info("Processing order{}, item {}, credit card number {}", orderMessage.getOrderNumber(),
                orderMessage.getItemName(), orderMessage.getCreditCardNumber());

        log.info("Headers are: ");
        headers.forEach(h -> log.info("key: {}, value: {}", h.key(), new String(h.value())));

        var bonusPercentage = Double.parseDouble(new String(headers.lastHeader("surpriseBonus").value()));
        var bonusAmount = (bonusPercentage / 100) * orderMessage.getPrice() * orderMessage.getQuantity();

        log.info("Surprise bonus is {}", bonusAmount);
    }
}












