package com.findtripmate.modules.matching.dto;

import lombok.Data;

@Data
public class MatchRequestDTO {

    private Double lat;
    private Double lng;
    private Double radius; // in km
}