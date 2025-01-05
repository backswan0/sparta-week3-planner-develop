package com.example.plan.plan7.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreatePlanRequestDto(
    @NotBlank(message = "Title input is required")
    @Length(
        min = 1,
        max = 20,
        message = "Title must be between 1 and 20 characters"
    )
    String title,

    @NotEmpty(message = "Null and empty values are forbidden")
    @Length(
        max = 200,
        message = "Task must be less than 200 characters"
    )
    String task,

    @NotNull(message = "Id input is required")
    Long memberId
) {

}