package com.example.plan.comment7.entity;

import com.example.plan.base.BaseEntity;
import com.example.plan.member7.entity.Member;
import com.example.plan.plan7.entity.Plan;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "comments7")
public class Comments extends BaseEntity {
    // 속성
    @Comment("댓글 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("댓글 내용")
    @Column(
            name = "content"
            , length = 255
    )
    private String content;

    @ManyToOne
    @JoinColumn(
            name = "plan_id"
            , nullable = false
    )
    private Plan plan;

    @ManyToOne
    @JoinColumn(
            name = "member_id"
            , nullable = false
    )
    private Member member;

    // 기본 생성자
    protected Comments() {
    }

    /**
     * 생성자
     *
     * @param content : 댓글 내용
     */
    public Comments(String content) {
        this.content = content;
    }

    /**
     * 기능
     * 댓글 내용 수정
     *
     * @param content : 수정하려는 댓글의 내용
     */
    public void update(String content) {
        this.content = content;
    }
}