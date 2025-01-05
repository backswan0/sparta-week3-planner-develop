package com.example.plan.member7.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record UpdateMemberRequestDto(
    @NotBlank(message = "Username input is required")
    @Length(
        min = 2,
        max = 20,
        message = "Username must be between 2 and 20 characters"
    )
    String username,

    @NotBlank(message = "Email input is required")
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        message = "Email must be in email format"
    )
    String email
) {

}