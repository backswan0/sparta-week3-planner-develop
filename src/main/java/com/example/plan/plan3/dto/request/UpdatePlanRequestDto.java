package com.example.plan.plan3.dto.request;

import lombok.Getter;

@Getter
public class UpdatePlanRequestDto {
    // 속성
    private final String title;
    private final String task;

    /**
     * 생성자
     *
     * @param title : 수정하려는 일정의 제목
     * @param task  : 수정하려는 일정의 내용
     */
    public UpdatePlanRequestDto(
            String title
            , String task
    ) {
        this.title = title;
        this.task = task;
    }
}