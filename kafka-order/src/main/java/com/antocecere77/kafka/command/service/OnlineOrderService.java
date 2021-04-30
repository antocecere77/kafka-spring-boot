package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.OnlineOrderRequest;
import com.antocecere77.kafka.command.action.OnlineOrderAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnlineOrderService {

	private final OnlineOrderAction action;

	public void saveOnlineOrder(OnlineOrderRequest request) {
		action.publishToKafka(request);
	}

}
