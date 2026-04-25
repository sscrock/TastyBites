package com.sourabh.fooddelivery.authservice.service;

import com.sourabh.fooddelivery.authservice.dto.LoginRequestDto;
import com.sourabh.fooddelivery.authservice.dto.LoginResponseDto;
import com.sourabh.fooddelivery.authservice.dto.RegisterRequestDto;

public interface AuthService {

    // ================= REGISTER =================
    void register(RegisterRequestDto request);

    // ================= LOGIN =================
    LoginResponseDto login(LoginRequestDto request);

    // ================= REFRESH TOKEN =================
    LoginResponseDto refreshToken(String refreshToken);
}