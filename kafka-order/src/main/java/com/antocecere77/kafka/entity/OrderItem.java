package com.antocecere77.kafka.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue
    private int orderItemId;

    @Column(nullable = false, length = 200)
    private String itemName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;
}
