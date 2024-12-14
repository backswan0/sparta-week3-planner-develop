package com.example.plan.plan.dto.request;

import lombok.Getter;

// 일정 수정 요청에 해당하는 request DTO
@Getter
public class UpdatePlanRequestDto {
    // 속성
    private final String newUsername;
    private final String newTitle;
    private final String newTask;

    /**
     * 생성자
     *
     * @param newUsername : 수정하려는 작성자 이름
     * @param newTitle    : 수정하려는 일정 제목
     * @param newTask     : 수정하려는 일정 내용
     */
    public UpdatePlanRequestDto(
            String newUsername
            , String newTitle
            , String newTask
    ) {
        this.newUsername = newUsername;
        this.newTitle = newTitle;
        this.newTask = newTask;
    }

    // 기능
}