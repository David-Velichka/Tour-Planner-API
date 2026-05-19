package com.tourplanner.service;

import com.tourplanner.dto.TourLogCreateRequestDto;
import com.tourplanner.dto.TourLogListResponseDto;
import com.tourplanner.dto.TourLogResponseDto;
import com.tourplanner.dto.TourLogUpdateRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TourLogService {

    public TourLogResponseDto createLog(Long userId, TourLogCreateRequestDto request) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public TourLogListResponseDto getLogs(Long userId, Long tourId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public TourLogResponseDto updateLog(Long userId, Long logId, TourLogUpdateRequestDto request) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public void deleteLog(Long userId, Long logId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
