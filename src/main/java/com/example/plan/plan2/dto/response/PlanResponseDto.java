package com.example.plan.plan2.dto.response;

import com.example.plan.member2.dto.response.MemberResponseDto;
import com.example.plan.member2.entity.Member;
import com.example.plan.plan2.entity.Plan;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponseDto {
    // 속성
    private final Long id;
    private final String title;
    private final String task;
    private final MemberResponseDto member;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 생성자
     *
     * @param id    : 일정 식별자
     * @param title : 일정 제목
     * @param task  : 일정 내용
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