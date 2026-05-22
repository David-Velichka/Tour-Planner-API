package com.tourplanner.service;

import com.tourplanner.dto.RouteRequestDto;
import com.tourplanner.dto.RouteResponseDto;
import com.tourplanner.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class RouteService {

    public RouteResponseDto getRoute(@Valid @NotNull RouteRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
