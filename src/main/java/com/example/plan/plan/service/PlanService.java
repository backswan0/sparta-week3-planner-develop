package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;

import java.util.List;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 *
 *
 */

public interface PlanService {

    public PlanResponseDto save(
            String name,
            String title,
            String task
    );

    public List<PlanResponseDto> findAll();

    public PlanResponseDto findById(Long id);
}