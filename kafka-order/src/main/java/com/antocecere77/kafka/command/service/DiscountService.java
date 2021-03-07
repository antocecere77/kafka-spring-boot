package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.DiscountRequest;
import com.antocecere77.kafka.command.action.DiscountAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountService {

	private final DiscountAction action;

	public void createDiscount(DiscountRequest request) {
		action.publishToKafka(request);
	}

}
