package com.example.plan.plan2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class PlanBaseEntity {
    @CreatedDate
    @Column (nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt; // 일정 작성일

    @LastModifiedDate
    @Column (nullable = false)
    private LocalDateTime updatedAt; // 일정 수정일
}