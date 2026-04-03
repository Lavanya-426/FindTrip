package com.findtripmate.modules.request.controller;

import com.findtripmate.modules.request.service.TripRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class TripRequestController {

    private final TripRequestService requestService;

    // Send Request
    @PostMapping
    public ResponseEntity<String> sendRequest(
            @RequestParam Long tripId,
            @RequestParam Long userId
    ) {

        requestService.sendRequest(tripId, userId);

        return ResponseEntity.ok("Request sent successfully");
    }
}