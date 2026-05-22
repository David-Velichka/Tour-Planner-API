package com.tourplanner.service;

import com.tourplanner.dto.TourLogCreateRequestDto;
import com.tourplanner.dto.TourLogListResponseDto;
import com.tourplanner.dto.TourLogResponseDto;
import com.tourplanner.dto.TourLogUpdateRequestDto;
import com.tourplanner.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class TourLogService {

    public TourLogResponseDto createLog(@NotNull Long userId, @Valid @NotNull TourLogCreateRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }

    public TourLogListResponseDto getLogs(@NotNull Long userId, @NotNull Long tourId) {
        throw new ServiceException("Not implemented yet.");
    }

    public TourLogResponseDto updateLog(
        @NotNull Long userId,
        @NotNull Long logId,
        @Valid @NotNull TourLogUpdateRequestDto request
    ) {
        throw new ServiceException("Not implemented yet.");
    }

    public void deleteLog(@NotNull Long userId, @NotNull Long logId) {
        throw new ServiceException("Not implemented yet.");
    }
}
