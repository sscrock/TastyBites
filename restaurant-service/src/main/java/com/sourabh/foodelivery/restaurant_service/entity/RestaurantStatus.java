package com.sourabh.foodelivery.restaurant_service.entity;

public enum RestaurantStatus {

    PENDING,     // waiting for admin approval
    APPROVED,    // approved and active
    REJECTED,    // rejected by admin
    CLOSED       // temporarily unavailable (optional)
}