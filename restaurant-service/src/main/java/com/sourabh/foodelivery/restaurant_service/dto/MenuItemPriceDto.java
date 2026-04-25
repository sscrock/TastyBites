package com.sourabh.foodelivery.restaurant_service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemPriceDto {

    private Long id;
    private BigDecimal price;
}