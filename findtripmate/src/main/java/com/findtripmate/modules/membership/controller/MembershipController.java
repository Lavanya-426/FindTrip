package com.findtripmate.modules.membership.controller;

import com.findtripmate.modules.membership.dto.MemberResponseDTO;
import com.findtripmate.modules.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    // Get members of a trip
    @GetMapping("/trip/{tripId}")
    public List<MemberResponseDTO> getMembers(@PathVariable Long tripId) {
        return membershipService.getMembers(tripId);
    }

    // Get member count
    @GetMapping("/trip/{tripId}/count")
    public long getMemberCount(@PathVariable Long tripId) {
        return membershipService.getMemberCount(tripId);
    }
}