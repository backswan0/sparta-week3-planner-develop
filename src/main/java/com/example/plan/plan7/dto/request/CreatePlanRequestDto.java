package com.example.plan.plan7.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreatePlanRequestDto(
        @NotBlank(message = "일정의 제목 입력은 필수입니다.")
        @Length(min = 1, max = 20)
        String title,

        @NotEmpty(message = "null과 빈값을 허용하지 않습니다. 공백으로 입력해 주세요.")
        @Length(max = 200)
        String task,

        @NotNull(message = "사용자 id 입력은 필수입니다.")
        Long memberId
) {
}