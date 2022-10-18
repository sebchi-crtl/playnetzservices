package com.playnetz.plan.service;

import com.playnetz.plan.model.Plans;
import com.playnetz.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public record PlanServiceImpl(PlanRepository planRepository) implements PlanService {

    @Override
    public Plans savePlan(Plans plan) {
        plan.setCreateTime(LocalDateTime.now());

        return planRepository.save(plan);
    }

    @Override
    public void deletePlan(Long planId) {
        planRepository.deleteById(planId);
    }

    @Override
    public List<Plans> findAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public Optional<Plans> findByPlanId(Long planId) {
        return planRepository.findById(planId);
    }
}
