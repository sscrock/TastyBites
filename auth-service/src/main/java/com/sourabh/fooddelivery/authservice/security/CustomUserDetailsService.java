package com.sourabh.fooddelivery.authservice.security;

import com.sourabh.fooddelivery.authservice.client.UserClient;
import com.sourabh.fooddelivery.authservice.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserResponseDto user;

        try {
            user = userClient.getUserByEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        String role = user.getRole() != null ? user.getRole() : "CUSTOMER";

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // encoded password
                .roles(role) // ✅ Spring will convert to ROLE_*
                .build();
    }
}