package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.OnlinePaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnlinePaymentProducer {

	private final KafkaTemplate<String, OnlinePaymentMessage> kafkaTemplate;

	public void publish(OnlinePaymentMessage message) {
		kafkaTemplate.send("t.commodity.online-payment", null, message.getOnlineOrderNumber(), message);
	}

}
