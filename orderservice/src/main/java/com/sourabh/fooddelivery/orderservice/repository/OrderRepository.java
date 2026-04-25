package com.sourabh.fooddelivery.orderservice.repository;

import com.sourabh.fooddelivery.orderservice.entity.Order;
import com.sourabh.fooddelivery.orderservice.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ✅ Pagination (production)
    Page<Order> findByUserId(Long userId, Pageable pageable);

    Page<Order> findByRestaurantId(Long restaurantId, Pageable pageable);

    Page<Order> findByUserIdAndStatus(Long userId, OrderStatus status, Pageable pageable);

    Page<Order> findByRestaurantIdAndStatus(Long restaurantId, OrderStatus status, Pageable pageable);

    // ✅ Optional (simple usage)
    List<Order> findByUserId(Long userId);
    List<Order> findByRestaurantId(Long restaurantId);
}