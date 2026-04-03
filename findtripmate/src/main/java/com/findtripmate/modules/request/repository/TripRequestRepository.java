package com.findtripmate.modules.request.repository;

import com.findtripmate.modules.request.entity.TripRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRequestRepository extends JpaRepository<TripRequest, Long> {

    boolean existsByTripIdAndUserId(Long tripId, Long userId);
}