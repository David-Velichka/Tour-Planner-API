package com.tourplanner.service;

import com.tourplanner.dto.TourCreateRequestDto;
import com.tourplanner.dto.TourListResponseDto;
import com.tourplanner.dto.TourResponseDto;
import com.tourplanner.dto.TourUpdateRequestDto;
import com.tourplanner.exception.ServiceException;
import com.tourplanner.model.entity.TourEntity;
import com.tourplanner.model.entity.UserEntity;
import com.tourplanner.repository.TourRepository;
import com.tourplanner.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    public TourService(TourRepository tourRepository, UserRepository userRepository) {
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
    }

    public TourResponseDto createTour(Long userId, TourCreateRequestDto request) {
        // Use a proxy reference to avoid an extra DB lookup just for the FK (Foreign Key)
        UserEntity user = userRepository.getReferenceById(userId);

        TourEntity tour = new TourEntity();
        tour.setUser(user);
        tour.setName(request.name());
        tour.setDescription(request.description());
        tour.setFrom(request.from());
        tour.setTo(request.to());
        tour.setTransportType(request.transportType());
        tour.setImageFilenameOrReference(request.imageFilePath());
        // distance/time/route are set via ORS integration later

        return toResponseDto(tourRepository.save(tour));
    }

    public TourListResponseDto getTours(Long userId) {
        List<TourEntity> tours = tourRepository.findAllByUserId(userId);
        List<TourResponseDto> dtos = tours.stream().map(this::toResponseDto).toList();
        return new TourListResponseDto(dtos);
    }

    public TourResponseDto updateTour(Long userId, Long tourId, TourUpdateRequestDto request) {
        TourEntity tour = tourRepository.findByIdAndUserId(tourId, userId)
            .orElseThrow(() -> new ServiceException("Tour not found."));

        tour.setName(request.name());
        tour.setDescription(request.description());
        tour.setFrom(request.from());
        tour.setTo(request.to());
        tour.setTransportType(request.transportType());
        tour.setImageFilenameOrReference(request.imageFilePath());

        return toResponseDto(tourRepository.save(tour));
    }

    public void deleteTour(Long userId, Long tourId) {
        TourEntity tour = tourRepository.findByIdAndUserId(tourId, userId)
            .orElseThrow(() -> new ServiceException("Tour not found."));
        // DB-level cascade on the FK in TourLogEntity removes related logs automatically
        tourRepository.delete(tour);
    }

    private TourResponseDto toResponseDto(TourEntity tour) {
        return new TourResponseDto(
            tour.getId(),
            tour.getName(),
            tour.getDescription(),
            tour.getFrom(),
            tour.getTo(),
            tour.getTransportType(),
            tour.getTourDistance(),
            tour.getEstimatedTime(),
            tour.getRouteInformation(),
            tour.getImageFilenameOrReference()
        );
    }
}
