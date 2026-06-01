package com.tourplanner.controller;

import com.tourplanner.dto.TourCreateRequestDto;
import com.tourplanner.dto.TourListResponseDto;
import com.tourplanner.dto.TourResponseDto;
import com.tourplanner.dto.TourUpdateRequestDto;
import com.tourplanner.service.TourService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tours")
@CrossOrigin(origins = "http://localhost:4200") // frontend port for CORS
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<TourResponseDto> createTour(
        @RequestHeader("X-User-Id") Long userId,
        @Valid @RequestBody TourCreateRequestDto request
    ) {
        return ResponseEntity.ok(tourService.createTour(userId, request));
    }

    @GetMapping
    public ResponseEntity<TourListResponseDto> getTours(
        @RequestHeader("X-User-Id") Long userId
    ) {
        return ResponseEntity.ok(tourService.getTours(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourResponseDto> updateTour(
        @RequestHeader("X-User-Id") Long userId,
        @PathVariable Long id,
        @Valid @RequestBody TourUpdateRequestDto request
    ) {
        return ResponseEntity.ok(tourService.updateTour(userId, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(
        @RequestHeader("X-User-Id") Long userId,
        @PathVariable Long id
    ) {
        tourService.deleteTour(userId, id);
        return ResponseEntity.noContent().build();
    }
}
