package com.antocecere77.kafka.api.request;

import com.antocecere77.kafka.util.LocalDateTimeDeserializer;
import com.antocecere77.kafka.util.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OnlineOrderRequest {

	private String onlineOrderNumber;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime orderDateTime;

	private int totalAmount;
	private String username;
}
