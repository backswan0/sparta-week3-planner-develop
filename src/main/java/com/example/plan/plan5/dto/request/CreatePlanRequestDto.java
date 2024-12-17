package com.example.plan.plan5.dto.request;

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
     * @param title  : 생성하려는 일정의 제목
     * @param task   : 생성하려는 일정의 내용
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
}