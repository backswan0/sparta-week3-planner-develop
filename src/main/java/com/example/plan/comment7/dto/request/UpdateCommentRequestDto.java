package com.example.plan.comment7.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UpdateCommentRequestDto(
    @NotBlank(message = "Comment input is required")
    @Length(
        max = 200,
        message = "Comment must be less than 200 characters"
    )
    String content
) {

}