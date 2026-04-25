package com.sourabh.fooddelivery.orderservice.service;

import com.sourabh.fooddelivery.orderservice.dto.OrderRequestDto;
import com.sourabh.fooddelivery.orderservice.dto.OrderResponseDto;
import com.sourabh.fooddelivery.orderservice.entity.OrderStatus;

import java.util.List;

public interface OrderService {

    // ================= CREATE =================
    OrderResponseDto createOrder(OrderRequestDto requestDto);

    // ================= GET =================
    OrderResponseDto getOrderById(Long orderId);

    List<OrderResponseDto> getOrdersByUserId(Long userId);

    List<OrderResponseDto> getOrdersByRestaurantId(Long restaurantId);

    // ================= UPDATE STATUS =================
    OrderResponseDto updateOrderStatus(Long orderId, OrderStatus status);

    // ================= CANCEL =================
    void cancelOrder(Long orderId);
}