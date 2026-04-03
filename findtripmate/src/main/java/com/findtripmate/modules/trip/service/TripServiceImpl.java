package com.findtripmate.modules.trip.service;

import com.findtripmate.modules.trip.dto.*;
import com.findtripmate.modules.trip.entity.Trip;
import com.findtripmate.modules.trip.repository.TripRepository;
import com.findtripmate.modules.user.entity.User;
import com.findtripmate.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Override
    public void createTrip(CreateTripRequestDTO request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Trip trip = Trip.builder()
                .source(request.getSource())
                .destination(request.getDestination())
                .sourceLat(request.getSourceLat())
                .sourceLng(request.getSourceLng())
                .destinationLat(request.getDestinationLat())
                .destinationLng(request.getDestinationLng())
                .departureTime(request.getDepartureTime())
                .seats(request.getSeats())
                .description(request.getDescription())
                .createdBy(user)
                .build();

        tripRepository.save(trip);
    }

    @Override
    public List<TripResponseDTO> getAllTrips() {
        return mapToDTO(tripRepository.findAll());
    }

    @Override
    public TripResponseDTO getTripById(Long tripId) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        return mapToDTO(List.of(trip)).get(0);
    }

    @Override
    public void deleteTrip(Long tripId, Long userId) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getCreatedBy().getId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        tripRepository.delete(trip);
    }

    // FILTER LOGIC
    @Override
    public List<TripResponseDTO> filterTrips(TripFilterDTO filter) {

        List<Trip> trips = tripRepository.filterTrips(
                filter.getSource(),
                filter.getDestination(),
                filter.getDepartureTime(),
                filter.getSeats()
        );

        return mapToDTO(trips);
    }

    // COMMON MAPPER (clean code)
    private List<TripResponseDTO> mapToDTO(List<Trip> trips) {
        return trips.stream()
                .map(trip -> TripResponseDTO.builder()
                        .id(trip.getId())
                        .source(trip.getSource())
                        .destination(trip.getDestination())
                        .departureTime(trip.getDepartureTime())
                        .seats(trip.getSeats())
                        .description(trip.getDescription())
                        .status(trip.getStatus().name())
                        .createdBy(trip.getCreatedBy().getId())
                        .build()
                )
                .toList();
    }
}