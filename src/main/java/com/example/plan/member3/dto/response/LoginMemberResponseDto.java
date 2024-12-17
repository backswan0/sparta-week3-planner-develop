package com.example.plan.member3.dto.response;

import lombok.Getter;

@Getter
public class LoginMemberResponseDto {
    private final Long id;

    public LoginMemberResponseDto(Long id) {
        this.id = id;
    }
}