package com.findtripmate.modules.membership.service;

import com.findtripmate.modules.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    @Override
    public long getMemberCount(Long tripId) {
        return membershipRepository.countByTripId(tripId);
    }
}