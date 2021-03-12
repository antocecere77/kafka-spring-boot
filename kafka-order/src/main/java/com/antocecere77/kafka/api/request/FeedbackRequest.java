package com.antocecere77.kafka.api.request;

import lombok.Data;

@Data
public class FeedbackRequest {

    private String feedback;

    private String location;

    private int rating;
}
