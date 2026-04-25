package com.sourabh.fooddelivery.orderservice.entity;

public enum OrderStatus {

    CREATED,
    CONFIRMED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED,
    CANCELLED;

    // ✅ Optional helper
    public boolean isFinalState() {
        return this == DELIVERED || this == CANCELLED;
    }
}