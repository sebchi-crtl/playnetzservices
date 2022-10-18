package com.playnetz.plan.controller;

import com.playnetz.plan.exception.PlanNotFoundException;
import com.playnetz.plan.model.Plans;
import com.playnetz.plan.service.PlanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("api/v1/plans")
@AllArgsConstructor
public class PlanController {


    private final PlanService planService;

    @PostMapping 
    public ResponseEntity<?> saveCourse(@RequestBody Plans plan)
    {
        return new ResponseEntity<>(planService.savePlan(plan), HttpStatus.CREATED);
    }

    @DeleteMapping("{planId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long planId)
    {
        planService.deletePlan(planId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPlans()
    {
        return ResponseEntity.ok(planService.findAllPlans());
    }

    @GetMapping("{planId}")
    public ResponseEntity<?> getPlan(@PathVariable Long planId)
    {
        return ResponseEntity.ok(planService.findByPlanId(planId));
    }

    @PutMapping("{planId}")
    public ResponseEntity<Plans> updatePlan(@PathVariable Long planId, @RequestBody Plans plan)
    {
        Plans updatePlan = planService.findByPlanId(planId)
                .orElseThrow(() -> new PlanNotFoundException("Plan not exist with id: " + planId));

        updatePlan.setCreateTime(LocalDateTime.now());
        updatePlan.setTitle(plan.getTitle());
        updatePlan.setSubtitle(plan.getSubtitle());
        updatePlan.setPrice(plan.getPrice());
        planService.savePlan(updatePlan);

        return ResponseEntity.ok(updatePlan);
    }
}
