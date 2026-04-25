package com.sourabh.fooddelivery.authservice.controller;

import com.sourabh.fooddelivery.authservice.dto.LoginRequestDto;
import com.sourabh.fooddelivery.authservice.dto.LoginResponseDto;
import com.sourabh.fooddelivery.authservice.dto.RegisterRequestDto;
import com.sourabh.fooddelivery.authservice.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // ================= REGISTER =================
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequestDto request) {
        authService.register(request);
        return "User registered successfully";
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    // ================= REFRESH =================
    @PostMapping("/refresh")
    public LoginResponseDto refresh(@RequestParam String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}