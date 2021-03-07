package com.antocecere77.kafka.api.request;

import lombok.Data;

@Data
public class OrderItemRequest {

    private String itemName;
    private int price;
    private int quantity;
}
