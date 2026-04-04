package com.findtripmate.modules.trip.service;

import com.findtripmate.modules.trip.dto.CreateTripRequestDTO;
import com.findtripmate.modules.trip.dto.TripResponseDTO;
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

    // CREATE TRIP
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

    // GET ALL TRIPS
    @Override
    public List<TripResponseDTO> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(this::mapSingleToDTO)
                .toList();
    }

    // GET TRIP BY ID
    @Override
    public TripResponseDTO getTripById(Long tripId) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        return mapSingleToDTO(trip);
    }

    // DELETE TRIP
    @Override
    public void deleteTrip(Long tripId, Long userId) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getCreatedBy().getId().equals(userId)) {
            throw new RuntimeException("Not authorized");
        }

        tripRepository.delete(trip);
    }

    // 🔁 SINGLE MAPPER
    private TripResponseDTO mapSingleToDTO(Trip trip) {
        return TripResponseDTO.builder()
                .id(trip.getId())
                .source(trip.getSource())
                .destination(trip.getDestination())
                .departureTime(trip.getDepartureTime())
                .seats(trip.getSeats())
                .description(trip.getDescription())
                .status(trip.getStatus().name())
                .createdBy(trip.getCreatedBy().getId())
                .build();
    }
}