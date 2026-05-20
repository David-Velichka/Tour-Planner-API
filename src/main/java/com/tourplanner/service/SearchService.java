package com.tourplanner.service;

import com.tourplanner.dto.SearchRequestDto;
import com.tourplanner.dto.SearchResponseDto;
import com.tourplanner.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    public SearchResponseDto search(Long userId, SearchRequestDto request) {
        throw new ServiceException("Not implemented yet.");
    }
}
