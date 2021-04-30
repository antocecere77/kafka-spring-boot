package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.OnlinePaymentRequest;
import com.antocecere77.kafka.command.action.OnlinePaymentAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnlinePaymentService {

	private final OnlinePaymentAction action;

	public void pay(OnlinePaymentRequest request) {
		action.publishPaymentToKafka(request);
	}

}
