package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;
import com.example.plan.plan.entity.Plan;
import com.example.plan.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public PlanResponseDto save(
            String username,
            String title,
            String task
    ) {
        Plan planToSave = new Plan(
                username,
                title,
                task
        );

        Plan savedPlan = planRepository.save(planToSave);

        return new PlanResponseDto(
                savedPlan.getPlanId(),
                savedPlan.getUsername(),
                savedPlan.getTitle(),
                savedPlan.getTask(),
                savedPlan.getCreatedAt(),
                savedPlan.getUpdatedAt()
        );
    }
}