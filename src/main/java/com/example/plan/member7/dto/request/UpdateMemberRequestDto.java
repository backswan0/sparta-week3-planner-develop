package com.example.plan.member7.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateMemberRequestDto(
        @NotBlank(message = "이름 입력은 필수입니다. 변경을 원하시지 않으면 가입 시 입력한 값을 입력해 주세요.")
        String username,

        @NotBlank(message = "이메일 입력은 필수입니다. 변경을 원하시지 않으면 가입 시 입력한 값을 입력해 주세요.")
        String email
) {
}