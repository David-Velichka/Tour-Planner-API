package com.tourplanner.dto;

public record TourLogCreateRequestDto(
    Long tourId,
    String loggedAt,
    String comment,
    Integer difficulty,
    Double totalDistanceKm,
    Integer totalTimeMin,
    Integer rating
) {
}
