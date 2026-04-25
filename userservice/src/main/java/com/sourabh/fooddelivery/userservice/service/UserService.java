package com.sourabh.fooddelivery.userservice.service;

import com.sourabh.fooddelivery.userservice.dto.UserRequestDto;
import com.sourabh.fooddelivery.userservice.dto.UserResponseDto;
import com.sourabh.fooddelivery.userservice.dto.UserUpdateDto;
import com.sourabh.fooddelivery.userservice.entity.User;

import java.util.List;

public interface UserService {

    // ================= CREATE USER =================
    UserResponseDto createUser(UserRequestDto requestDto);

    // ================= GET ALL USERS =================
    List<UserResponseDto> getAllUsers();

    // ================= GET USER BY ID =================
    UserResponseDto findUserById(Long id);

    // ================= GET USER BY EMAIL (PUBLIC) =================
    UserResponseDto getUserByEmail(String email);

    // ================= GET USER BY EMAIL (INTERNAL - AUTH SERVICE) =================
    User findUserEntityByEmail(String email);

    // ================= UPDATE (PARTIAL ONLY - BEST PRACTICE) =================
    UserResponseDto updateUser(Long id, UserUpdateDto requestDto);

    // ================= DELETE =================
    void deleteUser(Long id);
}