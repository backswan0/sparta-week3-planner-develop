package com.example.plan.plan.dto.request;

import lombok.Getter;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 * 일정 수정 완료 (작성일, 수정일까지 응답으로 보낸다는 가정하에)
 *
 */

@Getter
public class UpdatePlanRequestDto {
    private final String newUsername;
    private final String newTitle;
    private final String newTask;

    public UpdatePlanRequestDto(
            String newUsername,
            String newTitle,
            String newTask
    ) {
        this.newUsername = newUsername;
        this.newTitle = newTitle;
        this.newTask = newTask;
    }
}