package com.example.plan.member7.entity;

import com.example.plan.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "members7")
public class Member extends BaseEntity {
    // 속성
    @Comment("사용자 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("사용자 이름")
    @Column(
            name = "username"
            , nullable = false
            , length = 32
    )
    private String username;

    @Comment("사용자 이메일")
    @Column(
            name = "email"
            , nullable = false
            , unique = true
            , length = 128
    )
    private String email;

    @Comment("사용자 비밀번호")
    @Column(
            name = "password"
            , nullable = false
            , length = 255
    )
    private String password;

    protected Member() {
    }

    public Member(
            String username
            , String email
            , String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void update(
            String username
            , String email
    ) {
        this.username = username;
        this.email = email;
    }
}