package com.sourabh.foodelivery.restaurant_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDto {

    private Long id;

    // ================= BASIC INFO =================
    private String name;
    private String description;

    // ================= ADDRESS =================
    private String address;
    private String city;
    private String pincode;

    // ================= OWNER =================
    private Long ownerId;

    // ✅ Use String for microservice safety
    private String status;

    // ================= AUDITING =================
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}