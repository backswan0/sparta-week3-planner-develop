package com.example.plan.member5.dto.request;

import lombok.Getter;

@Getter
public class LoginMemberRequestDto {
    // 속성
    private final String email;
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