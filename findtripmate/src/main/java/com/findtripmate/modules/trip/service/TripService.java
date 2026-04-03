package com.findtripmate.modules.trip.service;

import com.findtripmate.modules.trip.dto.CreateTripRequestDTO;
import com.findtripmate.modules.trip.dto.TripFilterDTO;
import com.findtripmate.modules.trip.dto.TripResponseDTO;

import java.util.List;

public interface TripService {

    void createTrip(CreateTripRequestDTO request);

    List<TripResponseDTO> getAllTrips();

    TripResponseDTO getTripById(Long tripId);

    void deleteTrip(Long tripId, Long userId);

    List<TripResponseDTO> filterTrips(TripFilterDTO filter);
}