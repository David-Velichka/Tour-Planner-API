package com.tourplanner.controller;

import com.tourplanner.dto.TourLogCreateRequestDto;
import com.tourplanner.dto.TourLogListResponseDto;
import com.tourplanner.dto.TourLogResponseDto;
import com.tourplanner.dto.TourLogUpdateRequestDto;
import com.tourplanner.service.TourLogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tour-logs")
@CrossOrigin(origins = "http://localhost:4200") // frontend port for CORS
public class TourLogController {

    private final TourLogService tourLogService;

    public TourLogController(TourLogService tourLogService) {
        this.tourLogService = tourLogService;
    }

    @PostMapping
    public ResponseEntity<TourLogResponseDto> createLog(
        @RequestHeader("X-User-Id") Long userId,
        @Valid @RequestBody TourLogCreateRequestDto request
    ) {
        return ResponseEntity.ok(tourLogService.createLog(userId, request));
    }

    @GetMapping
    public ResponseEntity<TourLogListResponseDto> getLogs(
        @RequestHeader("X-User-Id") Long userId,
        @RequestParam Long tourId
    ) {
        return ResponseEntity.ok(tourLogService.getLogs(userId, tourId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourLogResponseDto> updateLog(
        @RequestHeader("X-User-Id") Long userId,
        @PathVariable Long id,
        @Valid @RequestBody TourLogUpdateRequestDto request
    ) {
        return ResponseEntity.ok(tourLogService.updateLog(userId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(
        @RequestHeader("X-User-Id") Long userId,
        @PathVariable Long id
    ) {
        tourLogService.deleteLog(userId, id);
        return ResponseEntity.noContent().build();
    }
}
