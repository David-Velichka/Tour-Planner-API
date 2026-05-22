package com.tourplanner.service;

import com.tourplanner.dto.ExportDataDto;
import com.tourplanner.dto.ImportRequestDto;
import com.tourplanner.dto.ImportResponseDto;
import com.tourplanner.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ImportExportService {

    public ExportDataDto exportData(@NotNull Long userId) {
        throw new ServiceException("Not implemented yet.");
    }

    public ImportResponseDto importData(@NotNull Long userId, @Valid @NotNull ImportRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
