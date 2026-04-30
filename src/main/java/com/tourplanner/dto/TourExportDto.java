package com.tourplanner.dto;

import com.tourplanner.model.entity.TransportType;
import java.util.List;

public record TourExportDto(
    String name,
    String description,
    String from,
    String to,
    TransportType transportType,
    Double distanceKm,
    Integer estimatedTimeMin,
    String routeGeometry,
    String imageFilePath,
    List<TourLogExportDto> logs
) {
}
