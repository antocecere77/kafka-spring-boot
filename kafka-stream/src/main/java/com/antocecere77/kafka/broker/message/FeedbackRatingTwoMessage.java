package com.antocecere77.kafka.broker.message;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class FeedbackRatingTwoMessage {

    private String location;
    private double averageRating;
    private Map<Integer, Long> ratingMap = new TreeMap<>();
}
