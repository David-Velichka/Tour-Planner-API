package com.tourplanner.service;

import com.tourplanner.dto.AuthLoginRequestDto;
import com.tourplanner.dto.AuthRegisterRequestDto;
import com.tourplanner.dto.AuthResponseDto;
import com.tourplanner.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class AuthService {

    public AuthResponseDto register(@Valid @NotNull AuthRegisterRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }

    public AuthResponseDto login(@Valid @NotNull AuthLoginRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
