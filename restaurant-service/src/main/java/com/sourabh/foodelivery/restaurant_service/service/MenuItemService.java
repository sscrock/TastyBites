package com.sourabh.foodelivery.restaurant_service.service;

import com.sourabh.foodelivery.restaurant_service.dto.MenuItemRequestDto;
import com.sourabh.foodelivery.restaurant_service.dto.MenuItemResponseDto;
import com.sourabh.foodelivery.restaurant_service.dto.MenuItemPriceDto;

import java.util.List;

public interface MenuItemService {

    // ================= CREATE =================
    MenuItemResponseDto createMenuItem(Long restaurantId, MenuItemRequestDto requestDto);

    // ================= GET =================
    List<MenuItemResponseDto> getMenuItemsByRestaurant(Long restaurantId);

    MenuItemResponseDto getMenuItemById(Long id);

    // 🔥 REQUIRED for Order Service
    MenuItemPriceDto getMenuItemPrice(Long id);

    // ================= UPDATE =================
    MenuItemResponseDto updateMenuItem(Long id, MenuItemRequestDto requestDto);

    // ================= DELETE =================
    void deleteMenuItem(Long id);
}