package com.findtripmate.modules.matching.service;

import com.findtripmate.modules.matching.dto.*;

import java.util.List;

public interface MatchingService {

    List<MatchResponseDTO> findMatchingTrips(MatchRequestDTO request);
}