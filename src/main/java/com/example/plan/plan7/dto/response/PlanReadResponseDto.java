package com.example.plan.plan7.dto.response;

import com.example.plan.member7.dto.response.MemberResponseDto;
import com.example.plan.member7.entity.Member;
import com.example.plan.plan7.entity.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanReadResponseDto {
    // 속성
    private final Long id;
    private final String title;
    private final String task;
    private final MemberResponseDto member;
    private final int totalComment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    /**
     * @param id           : 일정 식별자
     * @param title        : 일정 제목
     * @param task         : 일정 내용
     * @param createdAt    : 일정 생성일
     * @param updatedAt    : 일정 수정일
     * @param member       : 해당 일정을 작성한 사용자의 정보 (이메일, 이름 포함)
     * @param totalComment : 해당 일정에 있는 댓글 총 개수
     */
    public PlanReadResponseDto(
            Long id
            , String title
            , String task
            , LocalDateTime createdAt
            , LocalDateTime updatedAt
            , Member member
            , int totalComment
    ) {
        this.id = id;
        this.title = title;
        this.task = task;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = MemberResponseDto.toDto(member);
        this.totalComment = totalComment;
    }

    /**
     * 기능
     * 일정 엔티티와 댓글 총 개수를 '일정 목록 조회용 response DTO'로 변환하는 메서드
     *
     * @param plan         : 일정 엔티티
     * @param totalComment : 해당 일정에 있는 댓글 총 개수
     * @return PlanReadResponseDto
     */
    public static PlanReadResponseDto toDto(Plan plan, int totalComment) {
        return new PlanReadResponseDto(
                plan.getId()
                , plan.getTitle()
                , plan.getTask()
                , plan.getCreatedAt()
                , plan.getUpdatedAt()
                , plan.getMember()
                , totalComment
        );
    }
}