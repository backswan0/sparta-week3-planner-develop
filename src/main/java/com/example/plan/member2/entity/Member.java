package com.example.plan.member2.entity;

import com.example.plan.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "members2")
public class Member extends BaseEntity {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "username"
            , nullable = false
            , columnDefinition = "VARCHAR(16)"
    )
    private String username;

    @Column(name = "email"
            , nullable = false
            , columnDefinition = "VARCHAR(128)"
    )
    private String email;

    // 기본 생성자
    public Member() {
    }

    /**
     * 생성자
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     */
    public Member(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }

    /**
     * 기능
     * 사용자 정보 수정 (UPDATE - PUT)
     *
     * @param username : 수정하려는 사용자의 이름
     * @param email    : 수정하려는 사용자의 이메일
     */
    public void update(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }
}