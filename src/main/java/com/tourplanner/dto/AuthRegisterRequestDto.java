package com.tourplanner.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequestDto(
	@NotBlank String username,
	@NotBlank String password
) {
}
