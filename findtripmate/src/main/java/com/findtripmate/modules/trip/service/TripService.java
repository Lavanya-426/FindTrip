package com.findtripmate.modules.trip.service;

import com.findtripmate.modules.trip.entity.Trip;

public interface TripService {

    Trip createTrip(Trip trip, Long userId);
}