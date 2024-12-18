package com.example.plan.note7.entity;

import com.example.plan.base.BaseEntity;
import com.example.plan.member7.entity.Member;
import com.example.plan.plan7.entity.Plan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

/**
 * 댓글 C 완료
 */

@Getter
@Entity
@Table(name = "notes7")
public class Note extends BaseEntity {
    // 속성
    @Comment("댓글 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("댓글 내용")
    @Column(
            name = "content"
            , columnDefinition = "VARCHAR(255)"
    )
    private String content;

    @Setter
    @ManyToOne
    @JoinColumn(
            name = "plan_id"
            , nullable = false
    )
    private Plan plan;

    @Setter
    @ManyToOne
    @JoinColumn(
            name = "member_id"
            , nullable = false
    )
    private Member member;

    // 기본 생성자
    public Note() {

    }

    /**
     * 생성자
     *
     * @param content : 댓글 내용
     */
    public Note(String content) {
        this.content = content;
    }

    /**
     * 기능
     *
     * @param content : 수정하려는 댓글의 내용
     */
    public void update(String content) {
        this.content = content;
    }
}