package com.example.plan.comment7.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateCommentRequestDto(
        @NotBlank(message = "댓글 내용 입력은 필수입니다.")
        @Length(max = 200)
        String content,

        @NotNull(message = "댓글을 생성할 일정의 id 입력은 필수입니다.")
        Long planId
) {
}