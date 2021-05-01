package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.WebColorVoteRequest;
import com.antocecere77.kafka.command.action.WebColorVoteAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebColorVoteService {

	private final WebColorVoteAction action;

	public void createColorVote(WebColorVoteRequest request) {
		action.publishToKafka(request);
	}

}
