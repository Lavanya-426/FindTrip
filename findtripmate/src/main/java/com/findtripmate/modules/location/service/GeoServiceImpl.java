package com.findtripmate.modules.location.service;

import com.findtripmate.modules.location.client.MapsClient;
import com.findtripmate.modules.location.dto.LocationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    private final MapsClient mapsClient;

    @Override
    public List<LocationResponseDTO> searchLocation(String query) {

        List<Map<String, Object>> response = mapsClient.search(query);

        List<LocationResponseDTO> result = new ArrayList<>();

        if (response != null) {
            for (Map<String, Object> item : response) {

                LocationResponseDTO dto = LocationResponseDTO.builder()
                        .name((String) item.get("display_name"))
                        .lat(Double.parseDouble((String) item.get("lat")))
                        .lon(Double.parseDouble((String) item.get("lon")))
                        .build();

                result.add(dto);
            }
        }

        return result;
    }
}