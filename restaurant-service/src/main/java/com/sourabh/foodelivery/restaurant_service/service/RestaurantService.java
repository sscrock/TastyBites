package com.sourabh.foodelivery.restaurant_service.service;

import com.sourabh.foodelivery.restaurant_service.dto.RestaurantRequestDto;
import com.sourabh.foodelivery.restaurant_service.dto.RestaurantResponseDto;
import com.sourabh.foodelivery.restaurant_service.entity.RestaurantStatus;

import java.util.List;

public interface RestaurantService {

    RestaurantResponseDto createRestaurant(RestaurantRequestDto requestDto, Long ownerId);

    RestaurantResponseDto getRestaurantById(Long id);

    List<RestaurantResponseDto> getRestaurantsByOwner(Long ownerId);

    List<RestaurantResponseDto> getRestaurantsByCity(String city);

    RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto requestDto);

    RestaurantResponseDto updateRestaurantStatus(Long id, RestaurantStatus status);

    void deleteRestaurant(Long id);
    List<RestaurantResponseDto> getAllRestaurants();
}