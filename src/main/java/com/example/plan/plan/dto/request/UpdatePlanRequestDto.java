package com.example.plan.plan.dto.request;

import lombok.Getter;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 * 일정 수정 리팩토링 완료 (작성일, 수정일을 제외한다는 가정하에, transactional annotation 사용)
 * 삭제 완료
 */

@Getter
public class UpdatePlanRequestDto {
    private final String newUsername;
    private final String newTitle;
    private final String newTask;

    public UpdatePlanRequestDto(
            String newUsername
            , String newTitle
            , String newTask
    ) {
        this.newUsername = newUsername;
        this.newTitle = newTitle;
        this.newTask = newTask;
    }
}