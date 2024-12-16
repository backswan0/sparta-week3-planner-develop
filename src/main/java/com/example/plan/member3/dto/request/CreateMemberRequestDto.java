package com.example.plan.member3.dto.request;

import lombok.Getter;

@Getter
public class CreateMemberRequestDto {
    // 속성
    private final String username;
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