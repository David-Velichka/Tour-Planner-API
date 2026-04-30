package com.tourplanner.dto;

import com.tourplanner.model.entity.TransportType;

public record TourResponseDto(
    Long id,
    String name,
    String description,
    String from,
    String to,
    TransportType transportType,
    Double distanceKm,
    Integer estimatedTimeMin,
    String routeGeometry,
    String imageFilePath
) {
}
