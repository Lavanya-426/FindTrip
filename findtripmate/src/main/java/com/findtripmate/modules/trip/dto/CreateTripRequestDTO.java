package com.findtripmate.modules.trip.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTripRequestDTO {

    @NotBlank
    private String source;

    @NotBlank
    private String destination;

    private Double sourceLat;
    private Double sourceLng;

    private Double destinationLat;
    private Double destinationLng;

    private LocalDateTime departureTime;

    private Integer seats;

    private String description;

    private Long userId;
}