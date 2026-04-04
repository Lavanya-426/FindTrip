package com.findtripmate.modules.matching.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResponseDTO {

    private Long tripId;
    private String source;
    private String destination;
    private Double distance;
}