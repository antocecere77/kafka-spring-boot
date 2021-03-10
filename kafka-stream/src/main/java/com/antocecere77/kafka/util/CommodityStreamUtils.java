package com.antocecere77.kafka.util;

import com.antocecere77.kafka.broker.message.OrderMessage;
import org.apache.commons.lang3.StringUtils;

public class CommodityStreamUtils {

    public static OrderMessage maskCreditCard(OrderMessage original) {
        var converted = original.copy();
        var maskedCreditCardNumber = original.getCreditCardNumber()
                .replaceFirst("\\d{12}", StringUtils.repeat("*", 12));

        converted.setCreditCardNumber(maskedCreditCardNumber);

        return converted;
    }
}
