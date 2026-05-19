package com.tourplanner.service;

import com.tourplanner.dto.TourCreateRequestDto;
import com.tourplanner.dto.TourListResponseDto;
import com.tourplanner.dto.TourResponseDto;
import com.tourplanner.dto.TourUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    public TourResponseDto createTour(Long userId, TourCreateRequestDto request) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public TourListResponseDto getTours(Long userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public TourResponseDto updateTour(Long userId, Long tourId, TourUpdateRequestDto request) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void deleteTour(Long userId, Long tourId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
