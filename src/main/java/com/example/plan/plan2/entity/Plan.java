package com.example.plan.plan2.entity;

import com.example.plan.member2.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 * 유저 단건 조회 완료
 * 유저 전체 수정 완료
 * 유저 단건 삭제 완료
 * 유저 이름으로 many to one 설정 완료
 */

// 일정 엔티티. PlanBaseEntity를 상속했다.
@Getter
@Entity
@Table(name = "plans2")
public class Plan extends PlanBaseEntity {
    // 속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 일정 제목

    @Column(nullable = true, columnDefinition = "longtext")
    private String task; // 일정 내용

    @ManyToOne
    @JoinColumn(name = "members2_id")
    private Member member;

    // 기본 생성자
    public Plan () {

    }

    /**
     * 생성자
     *
     * @param title    : 일정 제목
     * @param task     : 일정 내용
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
     * @param title    : 수정하려는 일정 제목
     * @param task     : 수정하려는 일정 내용
     */
    public void update(
            String title
            , String task
    ) {
        this.title = title;
        this.task = task;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}