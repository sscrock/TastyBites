package com.sourabh.foodelivery.restaurant_service.service.impl;

import com.sourabh.foodelivery.restaurant_service.dto.RestaurantRequestDto;
import com.sourabh.foodelivery.restaurant_service.dto.RestaurantResponseDto;
import com.sourabh.foodelivery.restaurant_service.entity.Restaurant;
import com.sourabh.foodelivery.restaurant_service.entity.RestaurantStatus;
import com.sourabh.foodelivery.restaurant_service.mapper.RestaurantMapper;
import com.sourabh.foodelivery.restaurant_service.repository.RestaurantRepository;
import com.sourabh.foodelivery.restaurant_service.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // ================= CREATE =================
    @Override
    public RestaurantResponseDto createRestaurant(RestaurantRequestDto requestDto, Long ownerId) {

        Restaurant restaurant = RestaurantMapper.toEntity(requestDto, ownerId);

        Restaurant saved = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponseDto(saved);
    }

    // ================= GET BY ID =================
    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        return RestaurantMapper.toResponseDto(restaurant);
    }

    // ================= GET ALL =================
    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantMapper::toResponseDto)
                .toList();
    }

    // ================= GET BY OWNER =================
    @Override
    public List<RestaurantResponseDto> getRestaurantsByOwner(Long ownerId) {

        return restaurantRepository.findByOwnerId(ownerId)
                .stream()
                .map(RestaurantMapper::toResponseDto)
                .toList();
    }

    // ================= GET BY CITY =================
    @Override
    public List<RestaurantResponseDto> getRestaurantsByCity(String city) {

        return restaurantRepository.findByCity(city)
                .stream()
                .map(RestaurantMapper::toResponseDto)
                .toList();
    }

    // ================= UPDATE =================
    @Override
    public RestaurantResponseDto updateRestaurant(Long id, RestaurantRequestDto requestDto) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        if (requestDto.getName() != null) {
            restaurant.setName(requestDto.getName());
        }
        if (requestDto.getDescription() != null) {
            restaurant.setDescription(requestDto.getDescription());
        }
        if (requestDto.getAddress() != null) {
            restaurant.setAddress(requestDto.getAddress());
        }
        if (requestDto.getCity() != null) {
            restaurant.setCity(requestDto.getCity());
        }
        if (requestDto.getPincode() != null) {
            restaurant.setPincode(requestDto.getPincode());
        }

        Restaurant updated = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponseDto(updated);
    }

    // ================= STATUS UPDATE =================
    @Override
    public RestaurantResponseDto updateRestaurantStatus(Long id, RestaurantStatus status) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setStatus(status);

        Restaurant updated = restaurantRepository.save(restaurant);

        return RestaurantMapper.toResponseDto(updated);
    }

    // ================= DELETE =================
    @Override
    public void deleteRestaurant(Long id) {

        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException("Restaurant not found");
        }

        restaurantRepository.deleteById(id);
    }
}