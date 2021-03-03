package com.antocecere77.kafkaconsumer.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.List;

public class GlobalErrorHandler implements ConsumerAwareErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(FoodErrorHandler.class);

    @Override
    public void handle(Exception thrownException, List<ConsumerRecord<?, ?>> data, Consumer<?, ?> consumer, MessageListenerContainer container) {

    }

    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {
        log.warn("Global error handler for message: {}", data.value().toString());
    }
}
