package com.example.plan.member2.dto.request;

import lombok.Getter;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 * 유저 단건 조회 완료
 * 유저 전체 수정 완료
 * 유저 단건 삭제 완료
 */

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
