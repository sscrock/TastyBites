package com.sourabh.fooddelivery.orderservice.mapper;

import com.sourabh.fooddelivery.orderservice.dto.*;
import com.sourabh.fooddelivery.orderservice.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    // 🔹 OrderRequestDto → Order
    public static Order toEntity(OrderRequestDto dto) {
        return Order.builder()
                .userId(dto.getUserId())
                .restaurantId(dto.getRestaurantId())
                .deliveryAddress(dto.getDeliveryAddress())
                .status(OrderStatus.CREATED)
                .build();
    }

    // 🔹 OrderItemDto → OrderItem
    // ✅ price comes from backend (Restaurant Service)
    public static OrderItem toOrderItemEntity(OrderItemDto dto, Order order, BigDecimal price) {

        BigDecimal subtotal = price.multiply(BigDecimal.valueOf(dto.getQuantity()));

        return OrderItem.builder()
                .order(order)
                .menuItemId(dto.getMenuItemId())
                .quantity(dto.getQuantity())
                .price(price)
                .subtotal(subtotal)
                .build();
    }

    // 🔹 Order → Response DTO
    public static OrderResponseDto toResponseDto(Order order, List<OrderItem> items) {

        List<OrderItemResponseDto> itemDtos = items.stream()
                .map(item -> OrderItemResponseDto.builder()
                        .menuItemId(item.getMenuItemId())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .collect(Collectors.toList());

        return OrderResponseDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .totalAmount(order.getTotalAmount())
                .deliveryAddress(order.getDeliveryAddress())
                .status(order.getStatus().name())
                .items(itemDtos)
                .createdAt(order.getCreatedAt())
                .build();
    }
}