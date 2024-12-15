package com.example.plan.plan2.dto.request;

import lombok.Getter;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 * 유저 단건 조회 완료
 * 유저 전체 수정 완료
 * 유저 단건 삭제 완료
 * 유저 이름으로 many to one 설정 완료
 */

// 일정 생성 요청에 해당하는 request DTO
@Getter
public class CreatePlanRequestDto {
    private final String title;
    private final String task;
    private final String username;

    public CreatePlanRequestDto(
            String title
            , String task
            , String username

    ) {
        this.title = title;
        this.task = task;
        this.username = username;
    }
    // 기능
}