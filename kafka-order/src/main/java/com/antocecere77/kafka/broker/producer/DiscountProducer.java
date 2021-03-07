package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.DiscountMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountProducer {

	private final KafkaTemplate<String, DiscountMessage> kafkaTemplate;

	public void publish(DiscountMessage message) {
		kafkaTemplate.send("t.commodity.promotion", message);
	}

}
