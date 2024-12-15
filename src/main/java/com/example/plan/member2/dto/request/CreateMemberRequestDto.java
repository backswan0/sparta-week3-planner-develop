package com.example.plan.member2.dto.request;

import lombok.Getter;

@Getter
public class CreateMemberRequestDto {
    // 속성
    private final String username;
    private final String email;

    /**
     * 생성자
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     */
    public CreateMemberRequestDto(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }
}
