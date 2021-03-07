package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.OrderMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderMessage> kafkaTemplate;

    private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage orderMessage) {
        int surpriseBonus = StringUtils.startsWithIgnoreCase(orderMessage.getOrderLocation(), "A")
                ? 25
                : 15;

        List<Header> headers = new ArrayList<>();
        var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());
        headers.add(surpriseBonusHeader);

        return new ProducerRecord<String, OrderMessage>("t.commodity.order", null,
                orderMessage.getOrderNumber(), orderMessage, headers);
    }

    public void publish(OrderMessage message) {

        var producerRecord = buildProducerRecord(message);
        kafkaTemplate.send(producerRecord)
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










