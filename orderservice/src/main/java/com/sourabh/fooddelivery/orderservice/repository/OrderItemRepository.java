package com.sourabh.fooddelivery.orderservice.repository;

import com.sourabh.fooddelivery.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // ✅ Recommended
    List<OrderItem> findByOrderId(Long orderId);

    // Optional (keep if needed)
    List<OrderItem> findByOrder(com.sourabh.fooddelivery.orderservice.entity.Order order);
}