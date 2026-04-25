package com.sourabh.fooddelivery.authservice.client;

import com.sourabh.fooddelivery.authservice.dto.RegisterRequestDto;
import com.sourabh.fooddelivery.authservice.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserClient {

    // ================= CREATE USER =================
    @PostMapping("/api/users")
    UserResponseDto createUser(@RequestBody RegisterRequestDto request);

    // ================= GET USER BY EMAIL =================
    @GetMapping("/api/users/email/{email}")
    UserResponseDto getUserByEmail(@PathVariable String email);
}