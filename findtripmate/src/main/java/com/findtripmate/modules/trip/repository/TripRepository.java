package com.findtripmate.modules.trip.repository;

import com.findtripmate.modules.trip.entity.Trip;
import com.findtripmate.common.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByStatus(TripStatus status);

    List<Trip> findByDestinationContainingIgnoreCase(String destination);

     @Query("""
        SELECT t FROM Trip t
        WHERE (:source IS NULL OR LOWER(t.source) LIKE LOWER(CONCAT('%', :source, '%')))
          AND (:destination IS NULL OR LOWER(t.destination) LIKE LOWER(CONCAT('%', :destination, '%')))
          AND (:time IS NULL OR t.departureTime >= :time)
          AND (:seats IS NULL OR t.seats >= :seats)
          AND t.status = com.findtripmate.common.enums.TripStatus.ACTIVE
    """)
    List<Trip> filterTrips(
            @Param("source") String source,
            @Param("destination") String destination,
            @Param("time") LocalDateTime time,
            @Param("seats") Integer seats
    );
}

