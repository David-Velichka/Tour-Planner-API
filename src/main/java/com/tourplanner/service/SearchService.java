package com.tourplanner.service;

import com.tourplanner.dto.SearchRequestDto;
import com.tourplanner.dto.SearchResponseDto;
import com.tourplanner.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SearchService {

    public SearchResponseDto search(@NotNull Long userId, @Valid @NotNull SearchRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
