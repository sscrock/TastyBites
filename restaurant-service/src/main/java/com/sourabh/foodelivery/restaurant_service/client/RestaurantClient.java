package com.sourabh.foodelivery.restaurant_service.client;

import com.sourabh.foodelivery.restaurant_service.dto.MenuItemPriceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "restaurant-service", url = "http://localhost:8082")
public interface RestaurantClient {

    @GetMapping("/api/menu-items/{id}/price")
    MenuItemPriceDto getMenuItemPrice(@PathVariable Long id);
}