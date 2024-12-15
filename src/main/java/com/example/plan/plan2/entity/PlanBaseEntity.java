package com.example.plan.plan2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// update patch에서 사용자 이름 제외 리팩토링 완료

// [요구 사항] 작성일, 수정일 필드는 JPA Auditing을 활용한다.
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 작성일, 수정일 필드는 JPA Auditing을 활용한다.
public abstract class PlanBaseEntity {

    @CreatedDate
    @Column (nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt; // 작성일

    @LastModifiedDate
    @Column (nullable = false)
    private LocalDateTime updatedAt; // 수정일
}