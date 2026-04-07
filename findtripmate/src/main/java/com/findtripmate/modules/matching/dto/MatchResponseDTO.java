package com.findtripmate.modules.matching.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponseDTO {

    private Long tripId;
    private String source;
    private String destination;
    private Double distance;
    private LocalDateTime departureTime;
}