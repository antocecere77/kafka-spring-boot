package com.antocecere77.kafka.command.action;

import com.antocecere77.kafka.api.request.OrderItemRequest;
import com.antocecere77.kafka.api.request.OrderRequest;
import com.antocecere77.kafka.broker.message.OrderMessage;
import com.antocecere77.kafka.broker.producer.OrderProducer;
import com.antocecere77.kafka.entity.Order;
import com.antocecere77.kafka.entity.OrderItem;
import com.antocecere77.kafka.repository.OrderItemRepository;
import com.antocecere77.kafka.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAction {

    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Order convertToOrder(OrderRequest request) {

        var result = Order.builder()
                .creditCardNumber(request.getCreditCardNumber())
                .orderLocation(request.getOrderLocation())
                .orderDateTime(LocalDateTime.now())
                .orderNumber(RandomStringUtils.randomAlphanumeric(8).toUpperCase())
                .build();

        List<OrderItem> items = request.getItems()
                .stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toList());

        items.forEach(item -> item.setOrder(result));
        result.setItems(items);

        return result;
    }

    private OrderItem convertToOrderItem(OrderItemRequest orderItemRequest) {

        var result = OrderItem.builder()
                .itemName(orderItemRequest.getItemName())
                .price(orderItemRequest.getPrice())
                .quantity(orderItemRequest.getQuantity())
                .build();

        return result;
    }

    public void saveToDatabase(Order order) {
        orderRepository.save(order);
        order.getItems()
                .forEach(orderItemRepository::save);
    }

    public void publishToKafka(OrderItem orderItem) {

        OrderMessage orderMessage = OrderMessage.builder()
                .itemName(orderItem.getItemName())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .orderDateTime(orderItem.getOrder().getOrderDateTime())
                .orderLocation(orderItem.getOrder().getOrderLocation())
                .orderNumber(orderItem.getOrder().getOrderNumber())
                .creditCardNumber(orderItem.getOrder().getCreditCardNumber())
                .build();

        orderProducer.publish(orderMessage);
    }
}








