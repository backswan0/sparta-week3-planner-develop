package com.example.plan.plan2.entity;

import com.example.plan.common.entity.BaseEntity;
import com.example.plan.member2.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Comments;

@Getter
@Entity
@Table(name = "plans2")
public class Plan extends BaseEntity {
    // 속성
    @Comment("일정 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    /*
    [2의 배수로 작성한 이유 ex) 16, 512]
    데이터베이스에 따라 byte로 적용될 수 있는데, byte 단위가 이진법이라서
    즉, 호환성을 고려해서
     */
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
            , nullable = true
            , columnDefinition = "VARCHAR(512)"
    )
    private String task;

    @Setter
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
}