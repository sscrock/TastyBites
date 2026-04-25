package com.sourabh.fooddelivery.userservice.dto;

import com.sourabh.fooddelivery.userservice.entity.Role;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {

    private Long id;

    private String username;

    private String email;

    private String phoneNumber;

    private Role role;

    private LocalDateTime createdAt;
}