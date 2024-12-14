package com.example.plan.member2.dto.request;

import lombok.Getter;

@Getter
public class CreateMemberRequestDto {

    private final String username;

    private final String email;

    public CreateMemberRequestDto(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }
}
