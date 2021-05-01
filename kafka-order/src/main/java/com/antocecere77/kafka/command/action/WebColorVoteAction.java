package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.WebColorVoteRequest;
import com.antocecere77.kafka.broker.message.WebColorVoteMessage;
import com.antocecere77.kafka.broker.producer.WebColorVoteProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebColorVoteAction {

	private final WebColorVoteProducer producer;

	public void publishToKafka(WebColorVoteRequest request) {
		var message = new WebColorVoteMessage();

		message.setUsername(request.getUsername());
		message.setColor(request.getColor());
		message.setVoteDateTime(request.getVoteDateTime());

		producer.publish(message);
	}

}
