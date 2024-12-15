package com.example.plan.member2.dto.request;

import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {
    private String username;
    private String email;

    public UpdateMemberRequestDto (
            String username
            , String email
    ){
        this.username = username;
        this.email = email;
    }
}