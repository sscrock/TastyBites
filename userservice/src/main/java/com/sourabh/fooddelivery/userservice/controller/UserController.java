package com.sourabh.fooddelivery.userservice.controller;

import com.sourabh.fooddelivery.userservice.dto.UserRequestDto;
import com.sourabh.fooddelivery.userservice.dto.UserResponseDto;
import com.sourabh.fooddelivery.userservice.dto.UserUpdateDto;
import com.sourabh.fooddelivery.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ================= CREATE USER =================
    // 🔓 Called by Auth Service (internal use)
    @PostMapping
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    // ================= GET ALL USERS =================
    // 🔥 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    // ================= GET USER BY ID =================
    // 🔥 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    // ================= GET USER BY EMAIL =================
    // 🔥 ADMIN or SELF (email must match JWT username)
    @PreAuthorize("hasRole('ADMIN') or #email == authentication.name")
    @GetMapping("/email/{email}")
    public UserResponseDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // ================= UPDATE USER =================
    // 🔥 ADMIN ONLY (or SELF if you want later)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDto request) {
        return userService.updateUser(id, request);
    }

    // ================= DELETE USER =================
    // 🔥 ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}