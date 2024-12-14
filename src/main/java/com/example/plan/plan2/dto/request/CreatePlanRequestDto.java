package com.example.plan.plan2.dto.request;

import lombok.Getter;

@Getter
public class CreatePlanRequestDto {
    private final String username; // 작성 유저명
    private final String title; // 할일 제목
    private final String task; // 할일 내용

    public CreatePlanRequestDto(
            String username,
            String title,
            String task
    ) {
        this.username = username;
        this.title = title;
        this.task = task;
    }
}