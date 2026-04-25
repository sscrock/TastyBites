package com.sourabh.fooddelivery.userservice.mapper;

import com.sourabh.fooddelivery.userservice.dto.UserRequestDto;
import com.sourabh.fooddelivery.userservice.dto.UserResponseDto;
import com.sourabh.fooddelivery.userservice.dto.UserUpdateDto;
import com.sourabh.fooddelivery.userservice.entity.Role;
import com.sourabh.fooddelivery.userservice.entity.User;

public class UserMapper {

    // ✅ RequestDto -> Entity
    public static User toUser(UserRequestDto requestDto) {
        return User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword()) // encode in service
                .phoneNumber(requestDto.getPhoneNumber())
                .role(requestDto.getRole() != null ? requestDto.getRole() : Role.CUSTOMER)
                .build();
    }

    // ✅ Entity -> ResponseDto
    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }

    // ✅ UpdateDto -> Entity (partial update)
    public static void updateUserFromDto(UserUpdateDto dto, User user) {

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword()); // encode in service
        }
    }
}