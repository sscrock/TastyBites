package com.sourabh.fooddelivery.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Restaurant ID is required")
    private Long restaurantId;

    @NotBlank(message = "Delivery address is required")
    @Size(min = 5, max = 255)
    private String deliveryAddress;

    @NotNull(message = "Order items are required")
    @Size(min = 1, message = "At least one item is required")
    private List<OrderItemDto> items;
}