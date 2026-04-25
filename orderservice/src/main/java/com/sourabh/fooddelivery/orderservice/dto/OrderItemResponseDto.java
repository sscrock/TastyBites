package com.sourabh.fooddelivery.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDto {

    private Long menuItemId;

    private int quantity;

    private BigDecimal price;

    private BigDecimal subtotal;
}