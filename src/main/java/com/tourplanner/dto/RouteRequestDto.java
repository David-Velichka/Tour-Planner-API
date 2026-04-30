package com.tourplanner.dto;

import com.tourplanner.model.entity.TransportType;

public record RouteRequestDto(
    String from,
    String to,
    TransportType transportType
) {
}
