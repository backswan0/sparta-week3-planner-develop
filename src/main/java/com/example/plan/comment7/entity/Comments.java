package com.example.plan.comment7.entity;

import com.example.plan.base.BaseEntity;
import com.example.plan.member7.entity.Member;
import com.example.plan.plan7.entity.Plan;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "comments7")
public class Comments extends BaseEntity {

  @Comment("댓글 식별자")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "BIGINT")
  private Long id;

  @Comment("댓글 내용")
  @Column(
      name = "content",
      length = 255
  )
  private String content;

  @ManyToOne
  @JoinColumn(
      name = "plan_id",
      nullable = false
  )
  private Plan plan;

  @ManyToOne
  @JoinColumn(
      name = "member_id",
      nullable = false
  )
  private Member member;

  protected Comments() {
  }

  public Comments(String content) {
    this.content = content;
  }

  public void updatePlan(Plan plan) {
    this.plan = plan;
  }

  public void updateMember(Member member) {
    this.member = member;
  }

  public void updateContent(String content) {
    this.content = content;
  }
}