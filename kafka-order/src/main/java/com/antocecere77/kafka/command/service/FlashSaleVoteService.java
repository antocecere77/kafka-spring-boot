package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.FlashSaleVoteRequest;
import com.antocecere77.kafka.command.action.FlashSaleVoteAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlashSaleVoteService {

    @Autowired
    private FlashSaleVoteAction action;

    public void createFlashSaleVote(FlashSaleVoteRequest request) {
        action.publishToKafka(request);
    }

}