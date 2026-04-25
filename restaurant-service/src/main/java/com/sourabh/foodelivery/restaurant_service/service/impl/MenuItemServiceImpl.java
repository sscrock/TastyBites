package com.sourabh.foodelivery.restaurant_service.service.impl;

import com.sourabh.foodelivery.restaurant_service.dto.MenuItemRequestDto;
import com.sourabh.foodelivery.restaurant_service.dto.MenuItemResponseDto;
import com.sourabh.foodelivery.restaurant_service.dto.MenuItemPriceDto;
import com.sourabh.foodelivery.restaurant_service.entity.MenuItem;
import com.sourabh.foodelivery.restaurant_service.entity.Restaurant;
import com.sourabh.foodelivery.restaurant_service.mapper.MenuItemMapper;
import com.sourabh.foodelivery.restaurant_service.repository.MenuItemRepository;
import com.sourabh.foodelivery.restaurant_service.repository.RestaurantRepository;
import com.sourabh.foodelivery.restaurant_service.service.MenuItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    // ================= CREATE =================
    @Override
    public MenuItemResponseDto createMenuItem(Long restaurantId, MenuItemRequestDto requestDto) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem menuItem = MenuItemMapper.toEntity(requestDto);
        menuItem.setRestaurant(restaurant);

        MenuItem saved = menuItemRepository.save(menuItem);

        return MenuItemMapper.toResponseDto(saved);
    }

    // ================= GET BY RESTAURANT =================
    @Override
    public List<MenuItemResponseDto> getMenuItemsByRestaurant(Long restaurantId) {

        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(MenuItemMapper::toResponseDto)
                .toList();
    }

    // ================= GET BY ID =================
    @Override
    public MenuItemResponseDto getMenuItemById(Long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        return MenuItemMapper.toResponseDto(menuItem);
    }

    // 🔥 CRITICAL METHOD (for Order Service)
    @Override
    public MenuItemPriceDto getMenuItemPrice(Long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        return MenuItemMapper.toPriceDto(menuItem);
    }

    // ================= UPDATE =================
    @Override
    public MenuItemResponseDto updateMenuItem(Long id, MenuItemRequestDto requestDto) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        if (requestDto.getName() != null) {
            menuItem.setName(requestDto.getName());
        }

        if (requestDto.getDescription() != null) {
            menuItem.setDescription(requestDto.getDescription());
        }

        if (requestDto.getPrice() != null) {
            menuItem.setPrice(requestDto.getPrice());
        }

        if (requestDto.getAvailable() != null) {
            menuItem.setAvailable(requestDto.getAvailable());
        }

        MenuItem updated = menuItemRepository.save(menuItem);

        return MenuItemMapper.toResponseDto(updated);
    }

    // ================= DELETE =================
    @Override
    public void deleteMenuItem(Long id) {

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        menuItemRepository.delete(menuItem);
    }
}