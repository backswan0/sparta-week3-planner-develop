package com.example.plan.plan7.service;

import com.example.plan.plan7.dto.response.PlanResponseDto;
import com.example.plan.plan7.dto.response.ReadPlanResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

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