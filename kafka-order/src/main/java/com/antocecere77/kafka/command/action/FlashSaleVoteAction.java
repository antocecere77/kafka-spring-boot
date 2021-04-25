package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.FlashSaleVoteRequest;
import com.antocecere77.kafka.broker.message.FlashSaleVoteMessage;
import com.antocecere77.kafka.broker.producer.FlashSaleVoteProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlashSaleVoteAction {

    @Autowired
    private FlashSaleVoteProducer producer;

    public void publishToKafka(FlashSaleVoteRequest request) {
        var message = new FlashSaleVoteMessage();

        message.setCustomerId(request.getCustomerId());
        message.setItemName(request.getItemName());

        producer.publish(message);
    }

}
