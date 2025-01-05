package com.example.plan.member7.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignInMemberRequestDto(
    @NotBlank(message = "Email input is required")
    String email,

    @NotBlank(message = "Password input is required")
    String password
) {

}