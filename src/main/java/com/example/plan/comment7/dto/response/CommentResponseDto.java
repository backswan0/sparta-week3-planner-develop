package com.example.plan.comment7.dto.response;

import com.example.plan.comment7.entity.Comment;
import com.example.plan.plan7.dto.response.PlanResponseDto;
import com.example.plan.plan7.entity.Plan;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    // 속성
    private final Long id;
    private final String content;
    private final PlanResponseDto plan;

    /**
     * @param id      : 댓글 식별자
     * @param content : 댓글 내용
     * @param plan    : 댓글이 작성되는 일정
     */
    public CommentResponseDto(
            Long id
            , String content
            , Plan plan
    ) {
        this.id = id;
        this.content = content;
        this.plan = PlanResponseDto.toDto(plan);
    }

    /**
     * 기능
     * Comment 객체를 response DTO 타입으로 변환
     *
     * @param comment: Comment
     * @return CommentResponseDto
     */
    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId()
                , comment.getContent()
                , comment.getPlan()
        );
    }
}