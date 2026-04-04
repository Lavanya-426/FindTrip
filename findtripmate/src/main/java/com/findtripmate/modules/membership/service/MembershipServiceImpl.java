package com.findtripmate.modules.membership.service;

import com.findtripmate.modules.membership.dto.MemberResponseDTO;
import com.findtripmate.modules.membership.entity.TripMember;
import com.findtripmate.modules.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    @Override
    public long getMemberCount(Long tripId) {
        return membershipRepository.countByTripId(tripId);
    }

    @Override
    public List<MemberResponseDTO> getMembers(Long tripId) {

        return membershipRepository.findByTripId(tripId)
                .stream()
                .map(member -> MemberResponseDTO.builder()
                        .userId(member.getUser().getId())
                        .name(member.getUser().getName())
                        .role(member.getRole().name())
                        .build()
                )
                .toList();
    }
}