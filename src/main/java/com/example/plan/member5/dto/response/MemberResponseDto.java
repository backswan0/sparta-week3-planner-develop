package com.example.plan.member5.dto.response;

import com.example.plan.member5.entity.Member;
import lombok.Getter;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Getter
public class MemberResponseDto {
    // 속성
    private final Long id;
    private final String username;
    private final String email;

    /**
     * 생성자
     *
     * @param id       : 사용자의 식별자
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     */
    public MemberResponseDto(
            Long id
            , String username
            , String email
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * 기능
     * 사용자 엔티티를 response DTO로 변환
     *
     * @param member : 사용자 엔티티
     * @return MemberResponseDto
     */
    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId()
                , member.getUsername()
                , member.getEmail()
        );
    }
}