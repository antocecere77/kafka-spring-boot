package com.antocecere77.kafka.broker.message;


import com.antocecere77.kafka.util.LocalDateTimeDeserializer;
import com.antocecere77.kafka.util.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackMessage {

    private String feedback;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime feedbackDateTime;

    private String location;

    private int rating;
}

