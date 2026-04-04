package com.findtripmate.modules.lifecycle.scheduler;

import com.findtripmate.modules.lifecycle.service.LifecycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripScheduler {

    private final LifecycleService lifecycleService;

    // Runs every 1 minute
    @Scheduled(fixedRate = 60000)
    public void updateTrips() {
        lifecycleService.updateTripStatuses();
    }
}