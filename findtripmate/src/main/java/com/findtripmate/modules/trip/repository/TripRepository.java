package com.findtripmate.modules.trip.repository;

import com.findtripmate.modules.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}