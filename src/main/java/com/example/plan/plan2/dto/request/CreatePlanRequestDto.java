package com.example.plan.plan2.dto.request;

import lombok.Getter;

@Getter
public class CreatePlanRequestDto {
    // 속성
    private final String title;
    private final String task;
    private final Long userId;

    /**
     * 생성자
     *
     * @param title  : 일정 제목
     * @param task   : 일정 내용
     * @param userId : 해당 일정을 작성한 사용자의 식별자
     */
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