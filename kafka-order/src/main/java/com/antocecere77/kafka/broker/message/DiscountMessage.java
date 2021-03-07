package com.antocecere77.kafka.broker.message;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountMessage {

	private String discountCode;

	private int discountPercentage;
}
