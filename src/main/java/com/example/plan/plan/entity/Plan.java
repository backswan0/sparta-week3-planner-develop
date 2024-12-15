package com.example.plan.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;

// 일정 엔티티. PlanBaseEntity를 상속했다.
@Getter
@Entity
@Table(name = "plans")
public class Plan extends PlanBaseEntity {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 작성자 이름

    @Column(nullable = false)
    private String title; // 일정 제목

    @Column(nullable = true, columnDefinition = "longtext")
    private String task; // 일정 내용

    // 기본 생성자
    public Plan() {
    }

    /**
     * 생성자
     *
     * @param username : 작성자 이름
     * @param title    : 일정 제목
     * @param task     : 일정 내용
     */
    public Plan(
            String username
            , String title
            , String task
    ) {
        this.username = username;
        this.title = title;
        this.task = task;
    }

    /**
     * 기능
     * 일정 단건 수정에 해당하는 메서드 (UPDATE - PATCH: 작성자 이름은 수정에서 제외)
     *
     * @param title : 수정하려는 일정 제목
     * @param task  : 수정하려는 일정 내용
     */
    public void update(
            String title
            , String task
    ) {
        this.title = title;
        this.task = task;
    }
}