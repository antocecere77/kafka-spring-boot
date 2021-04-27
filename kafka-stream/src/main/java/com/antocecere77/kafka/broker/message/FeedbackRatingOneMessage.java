package com.antocecere77.kafka.broker.message;

import lombok.Data;

@Data
public class FeedbackRatingOneMessage {

    private String location;
    private double averageRating;
}
