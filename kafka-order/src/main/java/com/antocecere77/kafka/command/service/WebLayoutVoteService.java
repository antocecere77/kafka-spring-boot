package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.WebLayoutVoteRequest;
import com.antocecere77.kafka.command.action.WebLayoutVoteAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebLayoutVoteService {

	private final WebLayoutVoteAction action;

	public void createLayoutVote(WebLayoutVoteRequest request) {
		action.publishToKafka(request);
	}

}
