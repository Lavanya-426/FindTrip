package com.findtripmate.modules.location.controller;

import com.findtripmate.modules.location.dto.LocationResponseDTO;
import com.findtripmate.modules.location.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final GeoService geoService;

    @GetMapping("/search")
    public List<LocationResponseDTO> search(@RequestParam String query) {
        return geoService.searchLocation(query);
    }
}