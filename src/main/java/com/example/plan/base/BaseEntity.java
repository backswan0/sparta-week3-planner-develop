package com.example.plan.base;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Comment("생성일")
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(
            name = "created_at",
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    @Comment("수정일")
    @LastModifiedDate
    @Column(
            name = "updated_at",
            nullable = false,
            columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    private LocalDateTime updatedAt;

    @Comment("삭제 여부")
    @Column(
            name = "is_deleted",
            columnDefinition = "TINYINT(0)"
    )
    private Boolean isDeleted = false;

    @Comment("삭제일")
    @Column(
            name = "deleted_at",
            columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime deletedAt;

    public void markAsDeleted() {
        this.isDeleted = true; // 삭제 여부를 true로 설정
        this.deletedAt = LocalDateTime.now(); // 삭제일을 현재로 설정
    }
}