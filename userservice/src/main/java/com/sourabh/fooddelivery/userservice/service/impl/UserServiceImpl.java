package com.sourabh.fooddelivery.userservice.service.impl;

import com.sourabh.fooddelivery.userservice.dto.UserRequestDto;
import com.sourabh.fooddelivery.userservice.dto.UserResponseDto;
import com.sourabh.fooddelivery.userservice.dto.UserUpdateDto;
import com.sourabh.fooddelivery.userservice.entity.Role;
import com.sourabh.fooddelivery.userservice.entity.User;
import com.sourabh.fooddelivery.userservice.exception.UserNotFoundException;
import com.sourabh.fooddelivery.userservice.mapper.UserMapper;
import com.sourabh.fooddelivery.userservice.repository.UserRepository;
import com.sourabh.fooddelivery.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // ================= CREATE USER =================
    @Override
    public UserResponseDto createUser(UserRequestDto requestDto) {

        // ✅ Duplicate checks
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByPhoneNumber(requestDto.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        User user = UserMapper.toUser(requestDto);

        // 🔐 NEVER trust role from client
        user.setRole(Role.CUSTOMER);

        return UserMapper.toUserResponseDto(userRepository.save(user));
    }

    // ================= GET ALL USERS =================
    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserResponseDto)
                .toList();
    }

    // ================= GET USER BY ID =================
    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toUserResponseDto(user);
    }

    // ================= GET USER BY EMAIL =================
    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return UserMapper.toUserResponseDto(user);
    }

    // ================= INTERNAL METHOD (AUTH SERVICE) =================
    @Override
    public User findUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    // ================= UPDATE (PARTIAL) =================
    @Override
    public UserResponseDto updateUser(Long id, UserUpdateDto requestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserMapper.updateUserFromDto(requestDto, user);

        return UserMapper.toUserResponseDto(userRepository.save(user));
    }

    // ================= DELETE =================
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}