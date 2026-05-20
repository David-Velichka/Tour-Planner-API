package com.tourplanner.service;

import com.tourplanner.dto.ExportDataDto;
import com.tourplanner.dto.ImportRequestDto;
import com.tourplanner.dto.ImportResponseDto;
import com.tourplanner.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class ImportExportService {

    public ExportDataDto exportData(Long userId) {
        throw new ServiceException("Not implemented yet.");
    }

    public ImportResponseDto importData(Long userId, ImportRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
