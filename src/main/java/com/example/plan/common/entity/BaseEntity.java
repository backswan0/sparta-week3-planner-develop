package com.example.plan.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(
            name = "created_at"
            , nullable = false
            , updatable = false
            , columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt; // 일정 작성일

    @LastModifiedDate
    @Column(
            name = "updated_at"
            , nullable = false
            , updatable = true
            , columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    private LocalDateTime updatedAt; // 일정 수정일
}