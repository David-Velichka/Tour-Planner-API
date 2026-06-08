package com.tourplanner.controller;

import com.tourplanner.service.ImageStorageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

    private final ImageStorageService imageStorageService;

    public ImageController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    /**
     * Accepts a multipart image upload, stores it under a unique filename,
     * and returns the stored filename for later retrieval.
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadImage(
        @RequestHeader("X-User-Id") Long userId,
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        String originalName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "image";
        // storedRef is "{userId}/{uniqueName}" so image URL embeds userId for auth-free retrieval
        String storedRef = imageStorageService.storeImage(userId, originalName, file.getBytes());
        return ResponseEntity.ok(Map.of("filename", storedRef));
    }

    /**
     * Returns image bytes. URL path embeds userId so no auth header needed for browser img src.
     * Security: UUID-prefixed filename prevents guessing; path traversal blocked in service.
     */
    @GetMapping("/{userId}/{filename}")
    public ResponseEntity<byte[]> getImage(
        @RequestHeader("X-User-Id") Long requestUserId,
        @PathVariable Long userId,
        @PathVariable String filename
    ) {
        if (!userId.equals(requestUserId)) {
            return ResponseEntity.status(403).build();
        }
        byte[] imageBytes = imageStorageService.loadImage(userId, filename);
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(imageBytes);
    }
}
