package com.tourplanner.service;

import com.tourplanner.exception.ServiceException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ImageStorageService {

    public String storeImage(
        @NotNull Long userId,
        @NotBlank String originalFileName,
        @NotNull byte[] imageBytes
    ) {
        throw new ServiceException("Not implemented yet.");
    }

    public byte[] loadImage(@NotNull Long userId, @NotBlank String storedFileName) {
        throw new ServiceException("Not implemented yet.");
    }
}
