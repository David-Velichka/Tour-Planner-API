package com.tourplanner.dto;

import com.tourplanner.model.entity.TransportType;

public record TourUpdateRequestDto(
    String name,
    String description,
    String from,
    String to,
    TransportType transportType,
    String imageFilePath
) {
}
