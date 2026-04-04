package com.findtripmate.modules.membership.service;

import com.findtripmate.modules.membership.dto.MemberResponseDTO;

import java.util.List;

public interface MembershipService {

    long getMemberCount(Long tripId);

    List<MemberResponseDTO> getMembers(Long tripId);
}