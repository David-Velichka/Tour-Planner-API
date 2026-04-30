package com.tourplanner.dto;

public record RouteResponseDto(
    Double distanceKm,
    Integer estimatedTimeMin,
    String routeGeometry
) {
}
