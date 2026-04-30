package com.tourplanner.dto;

import java.util.List;

public record ImportRequestDto(List<TourExportDto> tours) {
}
