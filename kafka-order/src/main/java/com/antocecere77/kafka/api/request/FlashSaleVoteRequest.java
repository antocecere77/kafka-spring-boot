package com.antocecere77.kafka.api.request;

import lombok.Data;

@Data
public class FlashSaleVoteRequest {

    private String customerId;

    private String itemName;
}
