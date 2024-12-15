package com.example.plan.plan2.dto.request;

import lombok.Getter;

// 일정 생성 요청에 해당하는 request DTO
@Getter
public class CreatePlanRequestDto {
    private final String title;
    private final String task;
    private final Long userId;

    public CreatePlanRequestDto(
            String title
            , String task
            , Long userId

    ) {
        this.title = title;
        this.task = task;
        this.userId = userId;
    }
    // 기능
}