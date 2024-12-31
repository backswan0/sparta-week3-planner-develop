package com.example.plan.plan7.entity;

import com.example.plan.base.BaseEntity;
import com.example.plan.member7.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "plans7")
public class Plan extends BaseEntity {
    @Comment("일정 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("일정 제목")
    @Column(
            name = "title",
            nullable = false,
            length = 16
    )
    private String title;

    @Comment("일정 내용")
    @Column(
            name = "task",
            length = 255
    )
    private String task;

    @ManyToOne
    @JoinColumn(
            name = "member_id",
            nullable = false
    )
    private Member member;

    protected Plan() {
    }

    public Plan(
            String title,
            String task
    ) {
        this.title = title;
        this.task = task;
    }

    public void update(
            String title,
            String task
    ) {
        this.title = title;
        this.task = task;
    }

    public void update(Member member) {
        this.member = member;
    }
}