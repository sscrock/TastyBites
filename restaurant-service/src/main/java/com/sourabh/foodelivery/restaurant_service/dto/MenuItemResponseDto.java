package com.sourabh.foodelivery.restaurant_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemResponseDto {

    private Long id;

    private String name;
    private String description;

    private BigDecimal price;

    private Boolean available;

    private Long restaurantId;
    private String restaurantName;
}