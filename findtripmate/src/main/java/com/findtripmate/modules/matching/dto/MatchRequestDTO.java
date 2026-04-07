package com.findtripmate.modules.matching.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MatchRequestDTO {

    private Double lat;
    private Double lng;

    private Double radius; // km (e.g., 5)

    private LocalDateTime requestedTime; // user preferred time

    private int timeWindowMinutes; // e.g., 30
}