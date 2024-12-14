package com.example.plan.plan2.dto.response;

import com.example.plan.plan2.entity.Plan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {
    private final Long planId; // 할일 식별자
    private final String username; // 작성 유저명
    private final String title; // 할일 제목
    private final String task; // 할일 내용

    public PlanResponseDto(Long planId,
                           String username,
                           String title,
                           String task
    ) {
        this.planId = planId;
        this.username = username;
        this.title = title;
        this.task = task;
    }

    public static PlanResponseDto toDto(Plan plan) {
        return new PlanResponseDto(
                plan.getId()
                , plan.getUsername()
                , plan.getTitle()
                , plan.getTask()
        );
    }
}