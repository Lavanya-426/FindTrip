package com.findtripmate.modules.membership.repository;

import com.findtripmate.modules.membership.entity.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<TripMember, Long> {

    long countByTripId(Long tripId);

    List<TripMember> findByTripId(Long tripId);

    boolean existsByTripIdAndUserId(Long tripId, Long userId);
}