package com.tourplanner.service;

import com.tourplanner.dto.AuthLoginRequestDto;
import com.tourplanner.dto.AuthRegisterRequestDto;
import com.tourplanner.dto.AuthResponseDto;
import com.tourplanner.exception.ServiceException;
import com.tourplanner.model.entity.UserEntity;
import com.tourplanner.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponseDto register(@Valid @NotNull AuthRegisterRequestDto request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new ServiceException("Username already exists.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.username());
        user.setPasswordHash(request.password());

        UserEntity savedUser = userRepository.save(user);
        return new AuthResponseDto(savedUser.getId(), savedUser.getUsername());
    }

    public AuthResponseDto login(@Valid @NotNull AuthLoginRequestDto request) {
        // Simple credential check for student scope; no session or token handling.
        UserEntity user = userRepository.findByUsername(request.username())
            .orElseThrow(() -> new ServiceException("Invalid username or password."));

        if (!user.getPasswordHash().equals(request.password())) {
            throw new ServiceException("Invalid username or password.");
        }

        return new AuthResponseDto(user.getId(), user.getUsername());
    }
}
