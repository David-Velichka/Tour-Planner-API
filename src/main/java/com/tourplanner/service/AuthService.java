package com.tourplanner.service;

import com.tourplanner.dto.AuthLoginRequestDto;
import com.tourplanner.dto.AuthRegisterRequestDto;
import com.tourplanner.dto.AuthResponseDto;
import com.tourplanner.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponseDto register(AuthRegisterRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }

    public AuthResponseDto login(AuthLoginRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
