package com.sourabh.foodelivery.restaurant_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurants", indexes = {
        @Index(name = "idx_owner_id", columnList = "owner_id"),
        @Index(name = "idx_city", columnList = "city"),
        @Index(name = "idx_status", columnList = "restaurant_status")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    // ================= PRIMARY KEY =================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= BASIC INFO =================
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private String description;

    // ================= ADDRESS =================
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 6)
    private String pincode;

    // ================= OWNER =================
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    // ================= STATUS =================
    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_status", nullable = false)
    private RestaurantStatus status;

    // ================= AUDITING =================
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // ================= LIFECYCLE =================
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.status == null) {
            this.status = RestaurantStatus.PENDING;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}