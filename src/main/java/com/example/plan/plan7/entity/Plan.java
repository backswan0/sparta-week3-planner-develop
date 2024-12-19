package com.example.plan.plan7.entity;

import com.example.plan.base.BaseEntity;
import com.example.plan.member7.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "plans7")
public class Plan extends BaseEntity {
    // 속성
    @Comment("일정 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("일정 제목")
    @Column(
            name = "title"
            , nullable = false
            , columnDefinition = "VARCHAR(16)"
    )
    private String title;

    @Comment("일정 내용")
    @Column(
            name = "task"
            , columnDefinition = "VARCHAR(255)"
    )
    private String task;

    @ManyToOne
    @JoinColumn(
            name = "member_id"
            , nullable = false
    )
    private Member member;

    // 기본 생성자
    public Plan() {
    }

    /**
     * 생성자
     *
     * @param title : 일정 제목
     * @param task  : 일정 내용
     */
    public Plan(
            String title
            , String task
    ) {
        this.title = title;
        this.task = task;
    }

    /**
     * 기능
     * 일정 단건 수정에 해당하는 메서드 (UPDATE - PATCH)
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

    @SuppressWarnings("unused")
    public void setMember(Member member) {
        this.member = member;
    }
    /*
    TODO
     Setter 어노테이션을 쓰기 싫어서 직접 메서드를 구현했다.
     문제는 노란 줄이 아주 짙게 그어져서 @Setter를 썼다.
     그러다 해설 특강 때 최대한 '지양하는 편이 좋다'는 설명에 메서드를 다시 구현했다.
     그다음 노란 줄이 뜨지 않도록 @SuppressWarnings("unused")을 추가했다.
     */
}