package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.OnlineOrderRequest;
import com.antocecere77.kafka.broker.message.OnlineOrderMessage;
import com.antocecere77.kafka.broker.producer.OnlineOrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnlineOrderAction {

	private final OnlineOrderProducer producer;

	public void publishToKafka(OnlineOrderRequest request) {
		var message = new OnlineOrderMessage();

		message.setOnlineOrderNumber(request.getOnlineOrderNumber());
		message.setOrderDateTime(request.getOrderDateTime());
		message.setTotalAmount(request.getTotalAmount());
		message.setUsername(request.getUsername().toLowerCase());

		producer.publish(message);
	}

}
