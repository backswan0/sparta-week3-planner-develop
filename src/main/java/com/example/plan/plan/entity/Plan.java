package com.example.plan.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;

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

    public Plan(
            String username
            , String title
            , String task
    ) {
        this.username = username;
        this.title = title;
        this.task = task;
    }

    public void update(
            String newUsername
            , String newTitle
            , String newTask
    ) {
        this.username = newUsername;
        this.title = newTitle;
        this.task = newTask;
    }
}