package com.example.plan.plan.dto.request;

import lombok.Getter;

// 일정 생성 요청에 해당하는 request DTO
@Getter
public class CreatePlanRequestDto {
    // 속성
    private final String username;
    private final String title;
    private final String task;

    /**
     * 생성자
     *
     * @param username : 작성자 이름
     * @param title    : 일정 제목
     * @param task     : 일정 내용
     */
    public CreatePlanRequestDto(
            String username
            , String title
            , String task
    ) {
        this.username = username;
        this.title = title;
        this.task = task;
    }

    // 기능
}