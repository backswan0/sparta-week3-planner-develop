package com.example.plan.plan7.service;

import com.example.plan.plan7.dto.response.ReadPlanResponseDto;
import com.example.plan.plan7.dto.response.PlanResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanService {

    PlanResponseDto createPlan(
            String title,
            String task,
            Long memberId
    );

    List<ReadPlanResponseDto> readAllPlans(Pageable pageable);

    PlanResponseDto readPlanById(Long planId);

    PlanResponseDto updatePlan(
            Long planId,
            String title,
            String task
    );

    void deletePlan(Long planId);
}