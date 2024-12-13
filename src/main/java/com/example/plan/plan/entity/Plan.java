package com.example.plan.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 *
 *
 */

@Getter
@Entity
@Table(name = "plans")
public class Plan extends PlanBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 작성 유저명

    @Column(nullable = false)
    private String title; // 할일 제목

    @Column(nullable = true, columnDefinition = "longtext")
    private String task; // 할일 내용

    public Plan() {
    }

    // id는 자동 생성하므로 포함하지 않는다.
    public Plan(
            String username,
            String title,
            String task
    ) {
        this.username = username;
        this.title = title;
        this.task = task;
    }
}