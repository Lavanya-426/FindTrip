package com.findtripmate.modules.trip.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TripFilterDTO {

    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private Integer seats;
}