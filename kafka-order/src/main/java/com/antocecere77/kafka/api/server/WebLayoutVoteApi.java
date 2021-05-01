package com.antocecere77.kafka.api.server;

import com.antocecere77.kafka.api.request.WebLayoutVoteRequest;
import com.antocecere77.kafka.command.service.WebLayoutVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/web/layout/vote")
public class WebLayoutVoteApi {

	private final WebLayoutVoteService service;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createPrimary(@RequestBody WebLayoutVoteRequest request) {
		service.createLayoutVote(request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Layout vote created with layout : " + request.getLayout()
				+ ", by username : " + request.getUsername());
	}

}
