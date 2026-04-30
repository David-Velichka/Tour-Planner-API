package com.tourplanner.dto;

import com.tourplanner.model.entity.TransportType;

public record TourCreateRequestDto(
    String name,
    String description,
    String from,
    String to,
    TransportType transportType,
    String imageFilePath
) {
}
