package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;

import java.util.List;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 * 일정 수정 완료 (작성일, 수정일까지 응답으로 보낸다는 가정하에)
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

    public PlanResponseDto updatePlan(
            Long id,
            String newUsername,
            String newTitle,
            String newTask
    );
}