package com.antocecere77.kafkaconsumer.error.handler;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service(value = "myFoodOrderErrorHandler")
public class FoodErrorHandler implements ConsumerAwareListenerErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(FoodErrorHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
        log.warn("Found order error. Pretending to send to elasticsearch: {}, because: {} ", message.getPayload(), e.getMessage());
        return null;
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        return null;
    }
}
