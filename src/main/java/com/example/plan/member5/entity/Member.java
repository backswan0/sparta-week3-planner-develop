package com.example.plan.member5.entity;

import com.example.plan.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Getter
@Entity
@Table(name = "members5")
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
            , columnDefinition = "VARCHAR(32)"
    )
    private String username;

    @Comment("사용자 이메일")
    @Column(
            name = "email"
            , nullable = false
            , unique = true
            , columnDefinition = "VARCHAR(128)"
    )
    private String email;

    @Comment("사용자 비밀번호")
    @Column(
            name = "password"
            , nullable = false
            , columnDefinition = "VARCHAR(255)"
    )
    private String password;

    // 기본 생성자
    public Member() {
    }

    /**
     * 생성자
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     * @param password : 사용자의 비밀번호
     */
    public Member(
            String username
            , String email
            , String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * 기능
     * 사용자의 이름과 이메일 수정 (UPDATE - PUT)
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