package com.example.plan.comment7.dto.response;

import com.example.plan.comment7.entity.Comments;
import com.example.plan.plan7.entity.Plan;

public record CommentResponseDto(
        Long id,
        String content,
        Plan plan
) {

    public static CommentResponseDto toDto(
            Comments comments
    ) {
        return new CommentResponseDto(
                comments.getId(),
                comments.getContent(),
                comments.getPlan()
        );
    }
}