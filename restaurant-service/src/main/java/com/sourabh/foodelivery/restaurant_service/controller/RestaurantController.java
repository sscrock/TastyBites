package com.sourabh.foodelivery.restaurant_service.controller;

import com.sourabh.foodelivery.restaurant_service.dto.RestaurantRequestDto;
import com.sourabh.foodelivery.restaurant_service.dto.RestaurantResponseDto;
import com.sourabh.foodelivery.restaurant_service.entity.RestaurantStatus;
import com.sourabh.foodelivery.restaurant_service.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<RestaurantResponseDto> createRestaurant(
            @Valid @RequestBody RestaurantRequestDto requestDto,
            @RequestParam Long ownerId
    ) {
        return new ResponseEntity<>(
                restaurantService.createRestaurant(requestDto, ownerId),
                HttpStatus.CREATED
        );
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    // ================= GET BY OWNER =================
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<RestaurantResponseDto>> getByOwner(
            @PathVariable Long ownerId
    ) {
        return ResponseEntity.ok(
                restaurantService.getRestaurantsByOwner(ownerId)
        );
    }

    // ================= GET BY CITY =================
    @GetMapping("/city/{city}")
    public ResponseEntity<List<RestaurantResponseDto>> getByCity(
            @PathVariable String city
    ) {
        return ResponseEntity.ok(
                restaurantService.getRestaurantsByCity(city)
        );
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                restaurantService.updateRestaurant(id, requestDto)
        );
    }

    // ================= STATUS UPDATE (ADMIN) =================
    @PatchMapping("/{id}/status")
    public ResponseEntity<RestaurantResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam RestaurantStatus status
    ) {
        return ResponseEntity.ok(
                restaurantService.updateRestaurantStatus(id, status)
        );
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(
            @PathVariable Long id
    ) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}