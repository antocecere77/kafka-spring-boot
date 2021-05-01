package com.antocecere77.kafka.broker.producer;

import com.antocecere77.kafka.broker.message.WebColorVoteMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebColorVoteProducer {

	private final KafkaTemplate<String, WebColorVoteMessage> kafkaTemplate;

	public void publish(WebColorVoteMessage message) {
		kafkaTemplate.send("t.commodity.web.vote-color", message.getUsername(), message);
	}

}
