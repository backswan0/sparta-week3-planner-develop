package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;

import java.util.List;

public interface PlanService {

    public PlanResponseDto save(
            String name
            , String title
            , String task
    );

    public List<PlanResponseDto> findAll();

    public PlanResponseDto findById(Long id);

    public PlanResponseDto updatePlan(
            Long id
            , String newUsername
            , String newTitle
            , String newTask
    );

    public void delete(Long id);
}