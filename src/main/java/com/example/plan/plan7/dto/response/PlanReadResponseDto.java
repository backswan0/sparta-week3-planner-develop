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
    private final String title;
    private final String task;
    private final String username;
    private final int totalComment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    /**
     * @param title        : 일정 제목
     * @param task         : 일정 내용
     * @param createdAt    : 일정 생성일
     * @param updatedAt    : 일정 수정일
     * @param username       : 해당 일정을 작성한 사용자의 이름
     * @param totalComment : 해당 일정에 있는 댓글 총 개수
     */
    public PlanReadResponseDto(
            String title
            , String task
            , LocalDateTime createdAt
            , LocalDateTime updatedAt
            , String username
            , int totalComment
    ) {
        this.title = title;
        this.task = task;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.username = username;
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
                plan.getTitle()
                , plan.getTask()
                , plan.getCreatedAt()
                , plan.getUpdatedAt()
                , plan.getMember().getUsername()
                , totalComment
        );
    }
}