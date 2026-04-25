package com.sourabh.fooddelivery.authservice.service.impl;

import com.sourabh.fooddelivery.authservice.client.UserClient;
import com.sourabh.fooddelivery.authservice.dto.LoginRequestDto;
import com.sourabh.fooddelivery.authservice.dto.LoginResponseDto;
import com.sourabh.fooddelivery.authservice.dto.RegisterRequestDto;
import com.sourabh.fooddelivery.authservice.dto.UserResponseDto;
import com.sourabh.fooddelivery.authservice.entity.RefreshToken;
import com.sourabh.fooddelivery.authservice.repository.RefreshTokenRepository;
import com.sourabh.fooddelivery.authservice.service.AuthService;
import com.sourabh.fooddelivery.authservice.service.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;

    // ================= REGISTER =================
    @Override
    public void register(RegisterRequestDto request) {

        // 🔐 Encode password before sending to User Service
        request.setPassword(passwordEncoder.encode(request.getPassword()));

        try {
            userClient.createUser(request);
        } catch (Exception ex) {
            throw new RuntimeException("User already exists or invalid data");
        }
    }

    // ================= LOGIN =================
    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        // 🔐 Authenticate credentials
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception ex) {
            throw new RuntimeException("Invalid email or password");
        }

        // ✅ Fetch user from User Service
        UserResponseDto user = userClient.getUserByEmail(request.getEmail());

        // ✅ Generate access token
        String accessToken = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );

        // ✅ Generate refresh token
        String refreshToken = UUID.randomUUID().toString();

        RefreshToken tokenEntity = RefreshToken.builder()
                .email(user.getEmail())
                .token(refreshToken)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();

        // Remove old token (one active token per user)
        refreshTokenRepository.deleteByEmail(user.getEmail());
        refreshTokenRepository.save(tokenEntity);

        // ✅ Return response
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .role(user.getRole())
                .tokenType("Bearer")
                .build();
    }

    // ================= REFRESH TOKEN =================
    @Override
    public LoginResponseDto refreshToken(String refreshToken) {

        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        // ❌ Expired
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.deleteByEmail(token.getEmail());
            throw new RuntimeException("Refresh token expired");
        }

        // ✅ Fetch latest user data
        UserResponseDto user = userClient.getUserByEmail(token.getEmail());

        // ✅ Generate new access token
        String newAccessToken = jwtService.generateToken(
                user.getEmail(),
                user.getRole()
        );

        return LoginResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .role(user.getRole())
                .tokenType("Bearer")
                .build();
    }
}