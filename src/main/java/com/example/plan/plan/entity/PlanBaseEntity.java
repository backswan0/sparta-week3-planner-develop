package com.example.plan.plan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// [요구 사항] 작성일, 수정일 필드는 JPA Auditing을 활용한다.
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class PlanBaseEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt; // 일정 생성일

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt; // 일정 수정일
}