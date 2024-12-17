package com.example.plan.member3.dto.request;

import lombok.Getter;

@Getter
public class LoginMemberRequestDto {
    private final String email;
    private final String password;

    public LoginMemberRequestDto(
            String email
            , String password
    ) {
        this.email = email;
        this.password = password;
    }
}