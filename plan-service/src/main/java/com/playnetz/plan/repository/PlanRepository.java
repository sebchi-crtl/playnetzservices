package com.playnetz.plan.repository;

import com.playnetz.plan.model.Plans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plans, Long> {
    Optional<Plans> findById(Long planId);
}
