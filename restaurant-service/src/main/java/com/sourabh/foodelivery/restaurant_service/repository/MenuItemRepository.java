package com.sourabh.foodelivery.restaurant_service.repository;

import com.sourabh.foodelivery.restaurant_service.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // ✅ Pagination
    Page<MenuItem> findByRestaurantId(Long restaurantId, Pageable pageable);

    // ✅ Only available items
    Page<MenuItem> findByRestaurantIdAndAvailableTrue(Long restaurantId, Pageable pageable);
}