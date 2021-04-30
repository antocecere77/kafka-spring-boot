package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.OnlineOrderMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnlineOrderProducer {

	private final KafkaTemplate<String, OnlineOrderMessage> kafkaTemplate;

	public void publish(OnlineOrderMessage message) {
		kafkaTemplate.send("t.commodity.online-order", message.getOnlineOrderNumber(), message);
	}

}
