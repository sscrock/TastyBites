package com.sourabh.foodelivery.restaurant_service.controller;

import com.sourabh.foodelivery.restaurant_service.dto.*;
import com.sourabh.foodelivery.restaurant_service.service.MenuItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    // ================= CREATE =================
    @PostMapping("/restaurant/{restaurantId}")
    public ResponseEntity<MenuItemResponseDto> createMenuItem(
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuItemRequestDto requestDto
    ) {
        return new ResponseEntity<>(
                menuItemService.createMenuItem(restaurantId, requestDto),
                HttpStatus.CREATED
        );
    }

    // ================= GET BY RESTAURANT =================
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItemResponseDto>> getByRestaurant(
            @PathVariable Long restaurantId
    ) {
        return ResponseEntity.ok(
                menuItemService.getMenuItemsByRestaurant(restaurantId)
        );
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponseDto> getMenuItemById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    // 🔥 CRITICAL ENDPOINT (FOR ORDER SERVICE)
    @GetMapping("/{id}/price")
    public ResponseEntity<MenuItemPriceDto> getPrice(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(menuItemService.getMenuItemPrice(id));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponseDto> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItemRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                menuItemService.updateMenuItem(id, requestDto)
        );
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(
            @PathVariable Long id
    ) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}