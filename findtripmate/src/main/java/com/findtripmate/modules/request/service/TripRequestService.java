package com.findtripmate.modules.request.service;

public interface TripRequestService {

    void sendRequest(Long tripId, Long userId);
}