package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;

public interface PlanService {

    public PlanResponseDto save(
            String name,
            String title,
            String task
    );
}