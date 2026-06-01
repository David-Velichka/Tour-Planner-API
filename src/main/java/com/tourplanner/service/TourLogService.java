package com.tourplanner.service;

import com.tourplanner.dto.TourLogCreateRequestDto;
import com.tourplanner.dto.TourLogListResponseDto;
import com.tourplanner.dto.TourLogResponseDto;
import com.tourplanner.dto.TourLogUpdateRequestDto;
import com.tourplanner.exception.ServiceException;
import com.tourplanner.model.entity.TourEntity;
import com.tourplanner.model.entity.TourLogEntity;
import com.tourplanner.repository.TourLogRepository;
import com.tourplanner.repository.TourRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TourLogService {

    private final TourLogRepository tourLogRepository;
    private final TourRepository tourRepository;

    public TourLogService(TourLogRepository tourLogRepository, TourRepository tourRepository) {
        this.tourLogRepository = tourLogRepository;
        this.tourRepository = tourRepository;
    }

    public TourLogResponseDto createLog(Long userId, TourLogCreateRequestDto request) {
        // Verify the tour belongs to this user
        TourEntity tour = tourRepository.findByIdAndUserId(request.tourId(), userId)
            .orElseThrow(() -> new ServiceException("Tour not found."));

        TourLogEntity log = new TourLogEntity();
        log.setTour(tour);
        log.setDateTime(parseDateTime(request.loggedAt()));
        log.setComment(request.comment());
        log.setDifficulty(request.difficulty());
        log.setTotalDistance(request.totalDistanceKm());
        log.setTotalTime(request.totalTimeMin());
        log.setRating(request.rating());

        return toResponseDto(tourLogRepository.save(log));
    }

    public TourLogListResponseDto getLogs(Long userId, Long tourId) {
        // Verify the tour belongs to this user before returning its logs
        tourRepository.findByIdAndUserId(tourId, userId)
            .orElseThrow(() -> new ServiceException("Tour not found."));

        List<TourLogEntity> logs = tourLogRepository.findAllByTourId(tourId);
        List<TourLogResponseDto> dtos = logs.stream().map(this::toResponseDto).toList();
        return new TourLogListResponseDto(dtos);
    }

    public TourLogResponseDto updateLog(Long userId, Long logId, TourLogUpdateRequestDto request) {
        TourLogEntity log = tourLogRepository.findByIdAndTourUserId(logId, userId)
            .orElseThrow(() -> new ServiceException("Tour log not found."));

        log.setDateTime(parseDateTime(request.loggedAt()));
        log.setComment(request.comment());
        log.setDifficulty(request.difficulty());
        log.setTotalDistance(request.totalDistanceKm());
        log.setTotalTime(request.totalTimeMin());
        log.setRating(request.rating());

        return toResponseDto(tourLogRepository.save(log));
    }

    public void deleteLog(Long userId, Long logId) {
        TourLogEntity log = tourLogRepository.findByIdAndTourUserId(logId, userId)
            .orElseThrow(() -> new ServiceException("Tour log not found."));
        tourLogRepository.delete(log);
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr);
        } catch (DateTimeParseException ex) {
            throw new ServiceException("Invalid date-time format. Use ISO format: yyyy-MM-ddTHH:mm:ss");
        }
    }

    private TourLogResponseDto toResponseDto(TourLogEntity log) {
        return new TourLogResponseDto(
            log.getId(),
            log.getTour().getId(),
            log.getDateTime().toString(),
            log.getComment(),
            log.getDifficulty(),
            log.getTotalDistance(),
            log.getTotalTime(),
            log.getRating()
        );
    }
}
