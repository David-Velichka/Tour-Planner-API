package com.tourplanner.dto;

public record TourLogUpdateRequestDto(
    String loggedAt,
    String comment,
    Integer difficulty,
    Double totalDistanceKm,
    Integer totalTimeMin,
    Integer rating
) {
}
