package com.tourplanner.repository;

import com.tourplanner.model.entity.TourLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourLogRepository extends JpaRepository<TourLogEntity, Long> {

    // Ownership-safe lookup
    Optional<TourLogEntity> findByIdAndTourUserId(Long id, Long userId);
}
