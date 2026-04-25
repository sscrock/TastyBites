package com.sourabh.fooddelivery.orderservice.controller;

import com.sourabh.fooddelivery.orderservice.dto.OrderRequestDto;
import com.sourabh.fooddelivery.orderservice.dto.OrderResponseDto;
import com.sourabh.fooddelivery.orderservice.entity.OrderStatus;
import com.sourabh.fooddelivery.orderservice.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
            @Valid @RequestBody OrderRequestDto requestDto) {

        OrderResponseDto response = orderService.createOrder(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    // ================= GET BY USER =================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    // ================= GET BY RESTAURANT =================
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByRestaurant(
            @PathVariable Long restaurantId) {

        return ResponseEntity.ok(orderService.getOrdersByRestaurantId(restaurantId));
    }

    // ================= UPDATE STATUS =================
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    // ================= CANCEL =================
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {

        orderService.cancelOrder(id);
        return ResponseEntity.ok("Order cancelled successfully");
    }
}