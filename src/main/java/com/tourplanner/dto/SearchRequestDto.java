package com.tourplanner.dto;

import jakarta.validation.constraints.NotNull;

public record SearchRequestDto(@NotNull String query) {
}
