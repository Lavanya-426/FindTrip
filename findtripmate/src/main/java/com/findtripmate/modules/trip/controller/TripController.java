package com.findtripmate.modules.trip.controller;

import com.findtripmate.modules.trip.dto.*;
import com.findtripmate.modules.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity<String> createTrip(@RequestBody CreateTripRequestDTO request) {
        tripService.createTrip(request);
        return ResponseEntity.ok("Trip created successfully");
    }

    @GetMapping
    public ResponseEntity<List<TripResponseDTO>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripResponseDTO> getTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(tripService.getTripById(tripId));
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<String> deleteTrip(
            @PathVariable Long tripId,
            @RequestParam Long userId
    ) {
        tripService.deleteTrip(tripId, userId);
        return ResponseEntity.ok("Trip deleted successfully");
    }

    // FILTER ENDPOINT
    @PostMapping("/filter")
    public ResponseEntity<List<TripResponseDTO>> filterTrips(
            @RequestBody TripFilterDTO filter
    ) {
        return ResponseEntity.ok(tripService.filterTrips(filter));
    }
}