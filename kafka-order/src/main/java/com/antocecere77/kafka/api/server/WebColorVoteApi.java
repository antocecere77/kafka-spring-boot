package com.antocecere77.kafka.api.server;

import com.antocecere77.kafka.api.request.WebColorVoteRequest;
import com.antocecere77.kafka.command.service.WebColorVoteService;
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
@RequestMapping("/api/web/color/vote")
public class WebColorVoteApi {

	private final WebColorVoteService service;

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createColorVote(@RequestBody WebColorVoteRequest request) {
		service.createColorVote(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(
				"Color vote created with color : " + request.getColor() + ", by username : " + request.getUsername());
	}

}
