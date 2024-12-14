package com.example.plan.member2.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 * 유저 단건 조회 완료
 * 유저 전체 수정 완료
 *
 */

@Getter
@Entity
@Table(name = "members2")
public class Member extends MemberBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    public Member() {
    }

    public Member(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }

    public void update(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }
}