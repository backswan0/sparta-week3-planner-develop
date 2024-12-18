package com.example.plan.member5.dto.response;

import lombok.Getter;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
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