package com.antocecere77.kafka.api.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {

    private String orderLocation;
    private String creditCardNumber;
    private List<OrderItemRequest> items;

}
