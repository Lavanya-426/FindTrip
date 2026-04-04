package com.findtripmate.modules.matching.service;

import com.findtripmate.common.enums.TripStatus;
import com.findtripmate.modules.matching.dto.*;
import com.findtripmate.modules.trip.entity.Trip;
import com.findtripmate.modules.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService {

    private final TripRepository tripRepository;

    @Override
    public List<MatchResponseDTO> findMatchingTrips(MatchRequestDTO request) {

        List<Trip> trips = tripRepository.findByStatus(TripStatus.ACTIVE);

        return trips.stream()
                .map(trip -> {

                    double distance = calculateDistance(
                            request.getLat(),
                            request.getLng(),
                            trip.getSourceLat(),
                            trip.getSourceLng()
                    );

                    return MatchResponseDTO.builder()
                            .tripId(trip.getId())
                            .source(trip.getSource())
                            .destination(trip.getDestination())
                            .distance(distance)
                            .build();
                })
                .filter(res -> res.getDistance() <= request.getRadius())
                .toList();
    }

    // Haversine Formula
    private double calculateDistance(
            double lat1, double lon1,
            double lat2, double lon2
    ) {
        final int R = 6371; // Earth radius (km)

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}