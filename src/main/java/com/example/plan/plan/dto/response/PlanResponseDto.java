package com.example.plan.plan.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {
    private final Long planId; // 할일 식별자
    private final String username; // 작성 유저명
    private final String title; // 할일 제목
    private final String task; // 할일 내용
    private final LocalDateTime createdAt; // 작성일
    private final LocalDateTime updatedAt; // 수정일

    public PlanResponseDto(Long planId,
                           String username,
                           String title,
                           String task,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt
    ) {
        this.planId = planId;
        this.username = username;
        this.title = title;
        this.task = task;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}