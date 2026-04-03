package com.findtripmate.modules.request.service;

import com.findtripmate.common.enums.RequestStatus;
import com.findtripmate.common.enums.TripStatus;
import com.findtripmate.common.exception.CustomException;
import com.findtripmate.modules.request.entity.TripRequest;
import com.findtripmate.modules.request.repository.TripRequestRepository;
import com.findtripmate.modules.trip.entity.Trip;
import com.findtripmate.modules.trip.repository.TripRepository;
import com.findtripmate.modules.user.entity.User;
import com.findtripmate.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripRequestServiceImpl implements TripRequestService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripRequestRepository requestRepository;

    @Override
    public void sendRequest(Long tripId, Long userId) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new CustomException("Trip not found", HttpStatus.NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        // Cannot request own trip
        if (trip.getCreatedBy().getId().equals(userId)) {
            throw new CustomException("Cannot request your own trip", HttpStatus.BAD_REQUEST);
        }

        // Only ACTIVE trips
        if (trip.getStatus() != TripStatus.ACTIVE) {
            throw new CustomException("Trip is not active", HttpStatus.BAD_REQUEST);
        }

        //  Duplicate request
        if (requestRepository.existsByTripIdAndUserId(tripId, userId)) {
            throw new CustomException("Request already sent", HttpStatus.CONFLICT);
        }

        TripRequest request = TripRequest.builder()
                .trip(trip)
                .user(user)
                .status(RequestStatus.PENDING)
                .build();

        requestRepository.save(request);
    }
}