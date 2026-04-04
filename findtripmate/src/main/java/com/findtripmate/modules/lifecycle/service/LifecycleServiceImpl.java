package com.findtripmate.modules.lifecycle.service;

import com.findtripmate.common.enums.TripStatus;
import com.findtripmate.modules.trip.entity.Trip;
import com.findtripmate.modules.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LifecycleServiceImpl implements LifecycleService {

    private final TripRepository tripRepository;

    @Override
    public void updateTripStatuses() {

        List<Trip> activeTrips = tripRepository.findByStatus(TripStatus.ACTIVE);

        for (Trip trip : activeTrips) {

            // If trip time passed → mark COMPLETED
            if (trip.getDepartureTime().isBefore(LocalDateTime.now())) {

                trip.setStatus(TripStatus.COMPLETED);
                tripRepository.save(trip);
            }
        }
    }
}