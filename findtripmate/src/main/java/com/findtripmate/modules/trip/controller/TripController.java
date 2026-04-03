package com.findtripmate.modules.trip.controller;

import com.findtripmate.modules.trip.entity.Trip;
import com.findtripmate.modules.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    // Create Trip
    @PostMapping
    public ResponseEntity<Trip> createTrip(
            @RequestBody Trip trip,
            @RequestParam Long userId
    ) {

        Trip createdTrip = tripService.createTrip(trip, userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTrip);
    }
}