package com.findtripmate.modules.matching.controller;

import com.findtripmate.modules.matching.dto.*;
import com.findtripmate.modules.matching.service.MatchingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @PostMapping
    public List<MatchResponseDTO> matchTrips(@RequestBody MatchRequestDTO request) {
        return matchingService.findMatchingTrips(request);
    }
}