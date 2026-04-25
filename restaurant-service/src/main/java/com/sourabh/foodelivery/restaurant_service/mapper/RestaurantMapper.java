package com.sourabh.foodelivery.restaurant_service.mapper;

import com.sourabh.foodelivery.restaurant_service.dto.RestaurantRequestDto;
import com.sourabh.foodelivery.restaurant_service.dto.RestaurantResponseDto;
import com.sourabh.foodelivery.restaurant_service.entity.Restaurant;

public class RestaurantMapper {

    // ================= TO ENTITY =================
    public static Restaurant toEntity(RestaurantRequestDto dto, Long ownerId) {

        return Restaurant.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .address(dto.getAddress())
                .city(dto.getCity())
                .pincode(dto.getPincode())
                .ownerId(ownerId)
                .build();
    }

    // ================= TO RESPONSE =================
    public static RestaurantResponseDto toResponseDto(Restaurant restaurant) {

        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .city(restaurant.getCity())
                .pincode(restaurant.getPincode())
                .ownerId(restaurant.getOwnerId())
                .status(
                        restaurant.getStatus() != null
                                ? restaurant.getStatus().name()
                                : null
                )
                .createdAt(restaurant.getCreatedAt())
                .updatedAt(restaurant.getUpdatedAt())
                .build();
    }
}