package com.tourplanner.service;

import com.tourplanner.dto.TourCreateRequestDto;
import com.tourplanner.dto.TourListResponseDto;
import com.tourplanner.dto.TourResponseDto;
import com.tourplanner.dto.TourUpdateRequestDto;
import com.tourplanner.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class TourService {

    public TourResponseDto createTour(@NotNull Long userId, @Valid @NotNull TourCreateRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }

    public TourListResponseDto getTours(@NotNull Long userId) {
        throw new ServiceException("Not implemented yet.");
    }

    public TourResponseDto updateTour(
        @NotNull Long userId,
        @NotNull Long tourId,
        @Valid @NotNull TourUpdateRequestDto request
    ) {
        throw new ServiceException("Not implemented yet.");
    }

    public void deleteTour(@NotNull Long userId, @NotNull Long tourId) {
        throw new ServiceException("Not implemented yet.");
    }
}
