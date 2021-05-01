package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.WebLayoutVoteRequest;
import com.antocecere77.kafka.broker.message.WebLayoutVoteMessage;
import com.antocecere77.kafka.broker.producer.WebLayoutVoteProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebLayoutVoteAction {

	private final WebLayoutVoteProducer producer;

	public void publishToKafka(WebLayoutVoteRequest request) {
		var message = new WebLayoutVoteMessage();

		message.setUsername(request.getUsername());
		message.setLayout(request.getLayout());
		message.setVoteDateTime(request.getVoteDateTime());

		producer.publish(message);
	}

}
