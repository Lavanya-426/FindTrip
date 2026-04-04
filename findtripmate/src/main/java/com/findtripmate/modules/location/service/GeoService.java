package com.findtripmate.modules.location.service;

import com.findtripmate.modules.location.dto.LocationResponseDTO;

import java.util.List;

public interface GeoService {

    List<LocationResponseDTO> searchLocation(String query);
}