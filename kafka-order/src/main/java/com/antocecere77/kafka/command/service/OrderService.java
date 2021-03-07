package com.antocecere77.kafka.command.service;

import com.antocecere77.kafka.api.request.OrderRequest;
import com.antocecere77.kafka.command.action.OrderAction;
import com.antocecere77.kafka.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderAction action;

    public String saveOrder(OrderRequest request) {

        Order order = action.convertToOrder(request);
        action.saveToDatabase(order);

        order.getItems().forEach(action::publishToKafka);

        return order.getOrderNumber();
    }
}
