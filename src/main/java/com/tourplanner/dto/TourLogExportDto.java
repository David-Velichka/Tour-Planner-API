package com.tourplanner.dto;

public record TourLogExportDto(
    String loggedAt,
    String comment,
    Integer difficulty,
    Double totalDistanceKm,
    Integer totalTimeMin,
    Integer rating
) {
}
