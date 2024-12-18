package com.example.plan.plan5.dto.response;

import com.example.plan.member5.dto.response.MemberResponseDto;
import com.example.plan.member5.entity.Member;
import com.example.plan.plan5.entity.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Getter
public class PlanResponseDto {
    // 속성
    private final Long id;
    private final String title;
    private final String task;
    private final MemberResponseDto member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    /**
     *
     * @param id : 일정 식별자
     * @param title : 일정 제목
     * @param task : 일정 내용
     * @param createdAt : 일정 생성일
     * @param updatedAt : 일정 수정일
     * @param member : 해당 일정을 작성한 사용자의 정보 (이메일, 이름 포함)
     */
    public PlanResponseDto(
            Long id
            , String title
            , String task
            , LocalDateTime createdAt
            , LocalDateTime updatedAt
            , Member member
    ) {
        this.id = id;
        this.title = title;
        this.task = task;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = MemberResponseDto.toDto(member);
    }

    /**
     * 기능
     * 일정 엔티티를 response DTO로 변환하는 메서드
     *
     * @param plan: 일정 엔티티
     * @return PlanResponseDto
     */
    public static PlanResponseDto toDto(Plan plan) {
        return new PlanResponseDto(
                plan.getId()
                , plan.getTitle()
                , plan.getTask()
                , plan.getCreatedAt()
                , plan.getUpdatedAt()
                , plan.getMember()
        );
    }
}