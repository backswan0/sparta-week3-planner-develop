package com.example.plan.plan.dto.response;

import com.example.plan.plan.entity.Plan;
import lombok.Getter;

// 클라이언트에게 응답으로 전달하는 response DTO
@Getter
public class PlanResponseDto {
    // 속성
    private final Long id;
    private final String username;
    private final String title;
    private final String task;

    /**
     * 생성자
     *
     * @param id       : 일정 식별자
     * @param username : 작성자 이름
     * @param title    : 일정 제목
     * @param task     : 일정 내용
     */
    public PlanResponseDto(
            Long id
            , String username
            , String title
            , String task
    ) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.task = task;
    }

    /**
     * 기능
     * Plan 엔티티를 response DTO로 변환하는 메서드
     *
     * @param plan : Plan 엔티티
     * @return PlanResponseDto
     */
    public static PlanResponseDto toDto(Plan plan) {
        return new PlanResponseDto(
                plan.getId()
                , plan.getUsername()
                , plan.getTitle()
                , plan.getTask()
        );
    }
}