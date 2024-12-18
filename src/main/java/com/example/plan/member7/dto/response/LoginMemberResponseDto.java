package com.example.plan.member7.dto.response;

import lombok.Getter;

/**
 * 댓글 C 완료
 *
 *
 *
 *
 */

@Getter
public class LoginMemberResponseDto {
    // 속성
    private final Long id;

    /**
     * 생성자
     * @param id : 사용자의 식별자
     */
    public LoginMemberResponseDto(Long id) {
        this.id = id;
    }
}