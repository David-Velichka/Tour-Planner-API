package com.tourplanner.service;

import com.tourplanner.dto.TourCreateRequestDto;
import com.tourplanner.dto.TourListResponseDto;
import com.tourplanner.dto.TourResponseDto;
import com.tourplanner.dto.TourUpdateRequestDto;
import com.tourplanner.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    public TourResponseDto createTour(Long userId, TourCreateRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }

    public TourListResponseDto getTours(Long userId) {
        throw new ServiceException("Not implemented yet.");
    }

    public TourResponseDto updateTour(Long userId, Long tourId, TourUpdateRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }

    public void deleteTour(Long userId, Long tourId) {
        throw new ServiceException("Not implemented yet.");
    }
}
