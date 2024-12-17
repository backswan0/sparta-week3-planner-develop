package com.example.plan.member5.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

// 6단계까지 완료

@Getter
public class UpdateMemberRequestDto {
    // 속성
    @NotBlank(message = "이름 입력은 필수입니다. 변경을 원하시지 않으면 가입 시 입력한 값을 입력해 주세요.")
    private final String username;

    @NotBlank(message = "이메일 입력은 필수입니다. 변경을 원하시지 않으면 가입 시 입력한 값을 입력해 주세요.")
    private final String email;

    /**
     * 생성자
     *
     * @param username : 수정하려는 사용자의 이름
     * @param email    : 수정하려는 사용자의 이메일
     */
    public UpdateMemberRequestDto(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }
}