package com.example.plan.member5.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMemberRequestDto {
    // 속성
    @NotBlank(message ="사용자 이름은 필수입니다.")
    private final String username;
    // null 허용 x, 공백 허용 x, 빈값 허용 x

    private final String email;

    private final String password;

    /**
     * 생성자
     *
     * @param username : 사용자 이름
     * @param email    : 사용자 이메일
     */
    public CreateMemberRequestDto(
            String username
            , String email
            , String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}