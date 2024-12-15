package com.example.plan.member2.dto.response;

import com.example.plan.member2.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final Long id;
    private final String username;
    private final String email;

    public MemberResponseDto(
            Long id
            , String username
            , String email
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId()
                , member.getUsername()
                , member.getEmail()
        );
    }
}
