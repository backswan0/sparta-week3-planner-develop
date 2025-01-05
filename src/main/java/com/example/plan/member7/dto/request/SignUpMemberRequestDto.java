package com.example.plan.member7.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record SignUpMemberRequestDto(
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
    String email,

    @NotBlank(message = "Password input is required")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
        message = "Password must include a number, uppercase letter, lowercase letter, and special character"
    )
    @Length(
        min = 8,
        max = 20,
        message = "Password must be between 8 and 20 characters"
    )
    String password
) {

}