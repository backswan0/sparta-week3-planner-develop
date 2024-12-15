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

// 일정 수정 요청에 해당하는 request DTO
@Getter
public class UpdatePlanRequestDto {
    // 속성
    private final String title;
    private final String task;

    /**
     * 생성자
     *
     * @param title : 수정하려는 일정 제목
     * @param task  : 수정하려는 일정 내용
     */
    public UpdatePlanRequestDto(
            String title
            , String task
    ) {
        this.title = title;
        this.task = task;
    }

    // 기능
}