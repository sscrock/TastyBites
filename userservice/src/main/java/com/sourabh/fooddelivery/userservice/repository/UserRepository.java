package com.sourabh.fooddelivery.userservice.repository;

import com.sourabh.fooddelivery.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 🔑 Authentication
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    // ✅ Duplicate checks
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);
}