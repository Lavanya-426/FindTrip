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

    // Accept Request
    @PostMapping("/{requestId}/accept")
    public ResponseEntity<String> acceptRequest(
            @PathVariable Long requestId,
            @RequestParam Long ownerId
    ) {
        requestService.acceptRequest(requestId, ownerId);
        return ResponseEntity.ok("Request accepted successfully");
    }

    // Reject Request
    @PostMapping("/{requestId}/reject")
    public ResponseEntity<String> rejectRequest(
            @PathVariable Long requestId,
            @RequestParam Long ownerId
    ) {
        requestService.rejectRequest(requestId, ownerId);
        return ResponseEntity.ok("Request rejected successfully");
    }
}