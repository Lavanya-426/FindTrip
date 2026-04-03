package com.findtripmate.modules.trip.service;

import com.findtripmate.common.enums.MemberRole;
import com.findtripmate.common.exception.CustomException;
import com.findtripmate.modules.membership.entity.TripMember;
import com.findtripmate.modules.membership.repository.MembershipRepository;
import com.findtripmate.modules.trip.entity.Trip;
import com.findtripmate.modules.trip.repository.TripRepository;
import com.findtripmate.modules.user.entity.User;
import com.findtripmate.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    @Override
    public Trip createTrip(Trip trip, Long userId) {

        // 1. Get user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        // 2. Set creator
        trip.setCreatedBy(user);

        // 3. Save trip
        Trip savedTrip = tripRepository.save(trip);

        // 4. Add creator as member
        TripMember member = TripMember.builder()
                .trip(savedTrip)
                .user(user)
                .role(MemberRole.CREATOR)
                .build();

        membershipRepository.save(member);

        return savedTrip;
    }
}