package com.example.plan.comment7.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateCommentRequestDto(
        @NotBlank(message = "댓글 내용 입력은 필수입니다.")
        @Length(max = 200)
        String content
) {
}