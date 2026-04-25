package com.sourabh.foodelivery.restaurant_service.repository;

import com.sourabh.foodelivery.restaurant_service.entity.Restaurant;
import com.sourabh.foodelivery.restaurant_service.entity.RestaurantStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // ✅ Simple methods (used in your service)
    List<Restaurant> findByOwnerId(Long ownerId);
    List<Restaurant> findByCity(String city);

    // ✅ Advanced (for later)
    Page<Restaurant> findByOwnerId(Long ownerId, Pageable pageable);
    Page<Restaurant> findByCity(String city, Pageable pageable);
    Page<Restaurant> findByStatus(RestaurantStatus status, Pageable pageable);
}