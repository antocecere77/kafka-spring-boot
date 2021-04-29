package com.antocecere77.kafka.broker.message;

import java.time.LocalDateTime;

import com.antocecere77.kafka.util.LocalDateTimeDeserializer;
import com.antocecere77.kafka.util.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class InventoryMessage {

    private String item;
    private String location;
    private long quantity;
    private String type;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime transactionTime;
}
