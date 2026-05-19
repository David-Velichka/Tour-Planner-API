package com.tourplanner.service;

import org.springframework.stereotype.Service;

@Service
public class ImageStorageService {

    public String storeImage(Long userId, String originalFileName, byte[] imageBytes) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public byte[] loadImage(Long userId, String storedFileName) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
