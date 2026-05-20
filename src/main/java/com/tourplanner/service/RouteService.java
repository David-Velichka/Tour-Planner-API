package com.tourplanner.service;

import com.tourplanner.dto.RouteRequestDto;
import com.tourplanner.dto.RouteResponseDto;
import com.tourplanner.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    public RouteResponseDto getRoute(RouteRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
