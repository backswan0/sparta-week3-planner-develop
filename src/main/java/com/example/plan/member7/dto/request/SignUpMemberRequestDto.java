package com.example.plan.member7.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record SignUpMemberRequestDto(
        @NotBlank(message = "사용자 이름은 필수입니다.")
        @Length(min = 2, max = 20)
        String username,

        @NotBlank(message = "이메일 입력은 필수입니다.")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
                , message = "이메일 형식이 틀렸습니다. 다시 입력해 주세요."
        ) String email,

        @Length(min = 8, max = 20)
        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String password) {
}