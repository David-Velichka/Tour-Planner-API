package com.tourplanner.service;

import com.tourplanner.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class ImageStorageService {

    public String storeImage(Long userId, String originalFileName, byte[] imageBytes) {
        throw new ServiceException("Not implemented yet.");
    }

    public byte[] loadImage(Long userId, String storedFileName) {
        throw new ServiceException("Not implemented yet.");
    }
}
