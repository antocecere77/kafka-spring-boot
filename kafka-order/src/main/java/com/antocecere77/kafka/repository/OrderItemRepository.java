package com.antocecere77.kafka.repository;

import com.antocecere77.kafka.entity.Order;
import com.antocecere77.kafka.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>  {
}
