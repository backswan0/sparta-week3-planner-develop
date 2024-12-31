package com.example.plan.member7.dto.response;

import com.example.plan.member7.entity.Member;

public record MemberResponseDto(
        Long id,
        String username,
        String email
) {

    public static MemberResponseDto toDto(
            Member member
    ) {
        return new MemberResponseDto(
                member.getId()
                , member.getUsername()
                , member.getEmail()
        );
    }
}