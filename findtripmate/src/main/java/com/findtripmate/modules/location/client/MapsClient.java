package com.findtripmate.modules.location.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
@Component
@RequiredArgsConstructor
public class MapsClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> search(String query) {

        String url = "https://nominatim.openstreetmap.org/search?q="
+ query + "&format=json";
        @SuppressWarnings("unchecked")
        return restTemplate.getForObject(url, List.class);
    }
}