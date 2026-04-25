package com.sourabh.fooddelivery.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {

    private Long id;

    private Long userId;

    private Long restaurantId;

    // ✅ FIXED
    private BigDecimal totalAmount;

    private String deliveryAddress;

    private String status;

    // ✅ Use response DTO
    private List<OrderItemResponseDto> items;

    private LocalDateTime createdAt;
}