package com.tourplanner.controller;

import com.tourplanner.dto.ExportDataDto;
import com.tourplanner.dto.ImportRequestDto;
import com.tourplanner.dto.ImportResponseDto;
import com.tourplanner.service.ImportExportService;
import jakarta.validation.Valid;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ImportExportController {

    private final ImportExportService importExportService;
    private final ObjectMapper objectMapper;

    public ImportExportController(ImportExportService importExportService, ObjectMapper objectMapper) {
        this.importExportService = importExportService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportTours(
        @RequestHeader("X-User-Id") Long userId
    ) throws Exception {
        ExportDataDto data = importExportService.exportData(userId);
        byte[] json = objectMapper.writeValueAsBytes(data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDisposition(
            ContentDisposition.attachment().filename("tourplanner-export.json").build()
        );
        return ResponseEntity.ok().headers(headers).body(json);
    }

    @PostMapping("/import")
    public ResponseEntity<ImportResponseDto> importTours(
        @RequestHeader("X-User-Id") Long userId,
        @Valid @RequestBody ImportRequestDto request
    ) {
        return ResponseEntity.ok(importExportService.importData(userId, request));
    }
}
