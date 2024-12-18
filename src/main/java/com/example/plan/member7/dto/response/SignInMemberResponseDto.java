package com.example.plan.member7.dto.response;

import lombok.Getter;

@Getter
public class SignInMemberResponseDto {
    // 속성
    private final Long id;

    /**
     * 생성자
     * @param id : 사용자의 식별자
     */
    public SignInMemberResponseDto(Long id) {
        this.id = id;
    }
}