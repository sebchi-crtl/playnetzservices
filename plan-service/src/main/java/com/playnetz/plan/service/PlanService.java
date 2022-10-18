package com.playnetz.plan.service;

import com.playnetz.plan.model.Plans;

import java.util.List;
import java.util.Optional;

public interface PlanService
{
    Plans savePlan(Plans plan);

    void deletePlan(Long planId);

    List<Plans> findAllPlans();

    Optional<Plans> findByPlanId(Long planId);

}
