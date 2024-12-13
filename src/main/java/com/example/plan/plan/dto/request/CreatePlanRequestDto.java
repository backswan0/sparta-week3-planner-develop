package com.example.plan.plan.dto.request;

import lombok.Getter;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 * 일정 수정 리팩토링 완료 (작성일, 수정일을 제외한다는 가정하에, transactional annotation 사용)
 *
 */

@Getter
public class CreatePlanRequestDto {
    private final String username; // 작성 유저명
    private final String title; // 할일 제목
    private final String task; // 할일 내용

    public CreatePlanRequestDto(
            String username
            , String title
            , String task
    ) {
        this.username = username;
        this.title = title;
        this.task = task;
    }
}