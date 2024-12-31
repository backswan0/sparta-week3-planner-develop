package com.example.plan.comment7.dto.response;

import com.example.plan.comment7.entity.Comments;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id,
        Long planId,
        String content,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {
    public static CommentResponseDto toDto(
            Comments comments
    ) {
        return new CommentResponseDto(
                comments.getId(),
                comments.getPlan().getId(),
                comments.getContent(),
                comments.getCreatedAt()
        );
    }
}