package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.PromotionRequest;
import com.antocecere77.kafka.command.action.PromotionAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionAction action;

    public void createPromotion(PromotionRequest request) {
        action.publishToKafka(request);
    }
}
