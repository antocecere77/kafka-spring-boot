package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.OrderMessage;
import com.antocecere77.kafka.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    public void publish(OrderMessage message) {
        kafkaTemplate.send("t.commodity.order", message.getOrderNumber(), message)
                .addCallback(new ListenableFutureCallback<SendResult<String, OrderMessage>>() {

                    @Override
                    public void onSuccess(SendResult<String, OrderMessage> result) {
                        log.info("Order {}, item {} published successfully", message.getOrderNumber(), message.getItemName());
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        log.error("Order {}, item {} failed because {}",
                                message.getOrderNumber(),
                                message.getItemName(),
                                throwable.getMessage()
                        );
                    }
                });
    }
}










