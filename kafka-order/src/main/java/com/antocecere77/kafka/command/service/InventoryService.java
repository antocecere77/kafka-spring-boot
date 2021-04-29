package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.InventoryRequest;
import com.antocecere77.kafka.command.action.InventoryAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryAction action;

    public void addInventory(InventoryRequest request) {
        action.publishToKafka(request, "ADD");
    }

    public void subtractInventory(InventoryRequest request) {
        action.publishToKafka(request, "REMOVE");
    }

}
