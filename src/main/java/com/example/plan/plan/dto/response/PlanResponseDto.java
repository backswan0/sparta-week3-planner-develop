package com.example.plan.plan.dto.response;

import com.example.plan.plan.entity.Plan;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 * 일정 수정 완료 (작성일, 수정일까지 응답으로 보낸다는 가정하에)
 *
 */

@Getter
public class PlanResponseDto {
    private final Long id; // 할일 식별자
    private final String username; // 작성 유저명
    private final String title; // 할일 제목
    private final String task; // 할일 내용
    private final LocalDateTime createdAt; // 작성일
    private final LocalDateTime updatedAt; // 수정일

    public PlanResponseDto(Long id,
                           String username,
                           String title,
                           String task,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt
    ) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.task = task;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PlanResponseDto toDto(Plan plan) {
        return new PlanResponseDto(
                plan.getId(),
                plan.getUsername(),
                plan.getTitle(),
                plan.getTask(),
                plan.getCreatedAt(),
                plan.getUpdatedAt()
        );
    }
}