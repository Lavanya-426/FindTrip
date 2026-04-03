package com.findtripmate.modules.request.service;

public interface TripRequestService {

    void sendRequest(Long tripId, Long userId);
    void acceptRequest(Long requestId, Long ownerId);
    void rejectRequest(Long requestId, Long ownerId);

}