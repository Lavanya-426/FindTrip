package com.findtripmate.modules.membership.repository;

import com.findtripmate.modules.membership.entity.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<TripMember, Long> {

    long countByTripId(Long tripId);
}