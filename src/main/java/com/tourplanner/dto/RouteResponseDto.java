package com.tourplanner.dto;

// Route data retrieved from OpenRouteService for a given from/to/transport combination
public record RouteResponseDto(
    Double distanceKm,          // total route distance in kilometers
    Integer estimatedTimeMin,   // estimated travel time in minutes
    String routeGeometry        // GeoJSON coordinates array [[lon,lat],...] as JSON string
) {
}
