package com.example.plan.member5.dto.request;

import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {
    // 속성
    private final String username;
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