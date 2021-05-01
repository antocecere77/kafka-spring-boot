package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.WebLayoutVoteMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebLayoutVoteProducer {

	private final KafkaTemplate<String, WebLayoutVoteMessage> kafkaTemplate;

	public void publish(WebLayoutVoteMessage message) {
		kafkaTemplate.send("t.commodity.web.vote-layout", message.getUsername(), message);
	}

}
