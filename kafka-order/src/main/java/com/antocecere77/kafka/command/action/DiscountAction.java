package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.DiscountRequest;
import com.antocecere77.kafka.broker.message.DiscountMessage;
import com.antocecere77.kafka.broker.producer.DiscountProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountAction {

	private final DiscountProducer producer;

	public void publishToKafka(DiscountRequest request) {
		var message = new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage());
		producer.publish(message);
	}

}
