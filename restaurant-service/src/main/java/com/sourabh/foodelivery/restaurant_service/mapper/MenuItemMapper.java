package com.sourabh.foodelivery.restaurant_service.mapper;

import com.sourabh.foodelivery.restaurant_service.dto.MenuItemRequestDto;
import com.sourabh.foodelivery.restaurant_service.dto.MenuItemResponseDto;
import com.sourabh.foodelivery.restaurant_service.entity.MenuItem;

public class MenuItemMapper {

    // ================= TO ENTITY =================
    public static MenuItem toEntity(MenuItemRequestDto requestDto) {

        return MenuItem.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .available(
                        requestDto.getAvailable() != null
                                ? requestDto.getAvailable()
                                : true
                )
                .build();
    }

    // ================= TO RESPONSE =================
    public static MenuItemResponseDto toResponseDto(MenuItem menuItem) {

        return MenuItemResponseDto.builder()
                .id(menuItem.getId()) // ✅ FIXED
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .available(menuItem.getAvailable())

                // ✅ safe mapping
                .restaurantId(
                        menuItem.getRestaurant() != null
                                ? menuItem.getRestaurant().getId()
                                : null
                )
                .restaurantName(
                        menuItem.getRestaurant() != null
                                ? menuItem.getRestaurant().getName()
                                : null
                )

                .build();
    }
}