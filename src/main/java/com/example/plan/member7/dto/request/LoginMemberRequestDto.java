package com.example.plan.member7.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 댓글 C 완료
 *
 *
 *
 *
 */

@Getter
public class LoginMemberRequestDto {
    // 속성
    @NotBlank(message = "이메일 입력은 필수입니다.")
    private final String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private final String password;

    /**
     * 생성자
     *
     * @param email    : 사용자가 로그인하려고 입력한 이메일
     * @param password : 사용자가 로그인하려고 입력한 비밀번호
     */
    public LoginMemberRequestDto(
            String email
            , String password
    ) {
        this.email = email;
        this.password = password;
    }
}