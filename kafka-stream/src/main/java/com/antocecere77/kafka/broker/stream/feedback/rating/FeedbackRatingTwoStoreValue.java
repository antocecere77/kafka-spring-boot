package com.antocecere77.kafka.broker.stream.feedback.rating;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class FeedbackRatingTwoStoreValue {

    private Map<Integer, Long> ratingMap = new TreeMap<>();
}
