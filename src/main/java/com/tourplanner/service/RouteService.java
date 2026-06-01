package com.tourplanner.service;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.tourplanner.dto.RouteRequestDto;
import com.tourplanner.dto.RouteResponseDto;
import com.tourplanner.exception.ServiceException;
import com.tourplanner.model.entity.TransportType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.Duration;
import java.util.Map;

@Service
public class RouteService {

    private static final String ORS_BASE = "https://api.openrouteservice.org";

    @Value("${tourplanner.ors.api-key}")
    private String orsApiKey;

    // RestClient with explicit timeouts; reused across calls (thread-safe)
    private final RestClient restClient = buildRestClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static RestClient buildRestClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(5));
        factory.setReadTimeout(Duration.ofSeconds(30));
        return RestClient.builder()
            .baseUrl(ORS_BASE)
            .requestFactory(factory)
            .build();
    }

    public RouteResponseDto getRoute(RouteRequestDto request) {
        double[] fromCoords = geocode(request.from());
        double[] toCoords = geocode(request.to());
        return callDirections(fromCoords, toCoords, toProfile(request.transportType()));
    }

    // Geocodes a location string to [lon, lat] using ORS Pelias geocoding
    private double[] geocode(String location) {
        String json;
        try {
            json = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/geocode/search")
                    .queryParam("api_key", orsApiKey)
                    .queryParam("text", location)
                    .queryParam("size", 1)
                    .build())
                .retrieve()
                .body(String.class);
        } catch (RestClientException e) {
            throw new ServiceException("Geocoding service unavailable for: " + location, e);
        }

        try {
            JsonNode features = objectMapper.readTree(json).path("features");
            if (!features.isArray() || features.isEmpty()) {
                throw new ServiceException("Location not found: " + location);
            }
            JsonNode coords = features.get(0).path("geometry").path("coordinates");
            return new double[]{coords.get(0).asDouble(), coords.get(1).asDouble()};
        } catch (JacksonException e) {
            throw new ServiceException("Failed to parse geocoding response.", e);
        }
    }

    // Calls ORS Directions API and returns distance, time and route geometry
    private RouteResponseDto callDirections(double[] from, double[] to, String profile) {
        Map<String, Object> body = Map.of(
            "coordinates", new double[][]{{from[0], from[1]}, {to[0], to[1]}}
        );

        String json;
        try {
            json = restClient.post()
                .uri("/v2/directions/{profile}/geojson", profile)
                .header("Authorization", orsApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(String.class);
        } catch (RestClientException e) {
            throw new ServiceException("Route service unavailable.", e);
        }

        try {
            JsonNode feature = objectMapper.readTree(json).path("features").get(0);
            JsonNode summary = feature.path("properties").path("summary");
            double distMeters = summary.path("distance").asDouble();
            double durSeconds = summary.path("duration").asDouble();
            // Store coordinates as JSON string for Leaflet rendering in the UI
            String geometry = objectMapper.writeValueAsString(
                feature.path("geometry").path("coordinates")
            );
            return new RouteResponseDto(
                Math.round(distMeters / 10.0) / 100.0,   // meters -> km, 2 decimals
                (int) Math.ceil(durSeconds / 60.0),        // seconds -> minutes
                geometry
            );
        } catch (Exception e) {
            throw new ServiceException("Failed to parse route response.", e);
        }
    }

    // Maps TransportType to ORS routing profile
    private String toProfile(TransportType type) {
        return switch (type) {
            case BIKE -> "cycling-regular";
            case HIKE -> "foot-hiking";
            case RUNNING -> "foot-running";
            case VACATION -> "driving-car";
        };
    }
}
