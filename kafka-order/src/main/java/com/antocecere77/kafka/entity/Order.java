package com.antocecere77.kafka.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private int orderId;

    @Column(nullable = false, length = 20)
    private String orderNumber;

    @Column(nullable = false, length = 200)
    private String orderLocation;

    @Column(nullable = false)
    private LocalDate orderDateTime;

    @Column(nullable = false, length = 20)
    private String creditCardNumber;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
}
