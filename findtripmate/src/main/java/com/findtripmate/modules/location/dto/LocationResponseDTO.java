package com.findtripmate.modules.location.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponseDTO {

    private String name;
    private Double lat;
    private Double lon;
}