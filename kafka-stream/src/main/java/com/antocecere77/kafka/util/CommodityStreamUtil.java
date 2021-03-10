package com.antocecere77.kafka.util;

import com.antocecere77.kafka.broker.message.OrderMessage;
import com.antocecere77.kafka.broker.message.OrderPatternMessage;
import com.antocecere77.kafka.broker.message.OrderRewardMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.streams.kstream.Predicate;

public class CommodityStreamUtil {

    public static OrderMessage maskCreditCard(OrderMessage original) {
        var converted = original.copy();
        var maskedCreditCardNumber = original.getCreditCardNumber()
                .replaceFirst("\\d{12}", StringUtils.repeat("*", 12));

        converted.setCreditCardNumber(maskedCreditCardNumber);

        return converted;
    }

    public static OrderPatternMessage mapToOrderPattern(OrderMessage original) {
        var result = OrderPatternMessage.builder()
                .itemName(original.getItemName())
                .orderDateTime(original.getOrderDateTime())
                .orderLocation(original.getOrderLocation())
                .orderNumber(original.getOrderNumber())
                .build();

        var totalItemAmount = original.getPrice() * original.getQuantity();
        result.setTotalItemAmount(totalItemAmount);

        return result;
    }

    public static OrderRewardMessage mapToOrderReward(OrderMessage original) {
        var result = OrderRewardMessage.builder()
                .itemName(original.getItemName())
                .orderDateTime(original.getOrderDateTime())
                .orderLocation(original.getOrderLocation())
                .orderNumber(original.getOrderNumber())
                .price(original.getPrice())
                .quantity(original.getQuantity())
                .build();

        return result;
    }

    public static Predicate<String, OrderMessage> isLargeQuantity() {
        return (key, value) -> value.getQuantity() > 200;
    }

}
