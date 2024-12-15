package com.example.plan.plan2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 * 유저 단건 조회 완료
 * 유저 전체 수정 완료
 * 유저 단건 삭제 완료
 * 유저 이름으로 many to one 설정 완료
 */

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