package com.sourabh.fooddelivery.authservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;

    private String tokenType = "Bearer";

    // ✅ keep it simple
    private Long userId;
    private String email;
    private String role;
}