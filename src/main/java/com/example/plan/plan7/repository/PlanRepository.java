package com.example.plan.plan7.repository;

import com.example.plan.plan7.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * 댓글 C 완료
 *
 *
 *
 *
 */

public interface PlanRepository extends JpaRepository<Plan, Long> {
    /**
     * 기능
     * 일정의 식별자로 일정 단건 조회
     *
     * @param id : 일정의 식별자
     * @return Plan
     */
    default Plan findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                        , "입력된 id가 존재하지 않습니다. 다시 입력해 주세요."
                )
        );
    }

    @Transactional
    @Modifying
    @Query(
            "UPDATE Plan p " +
            "SET p.isDeleted = true, p.deletedAt = CURRENT_TIMESTAMP " +
            "WHERE p.id = :id " +
            "AND p.isDeleted IS NULL"
    )
    int softDeleteById(Long id);

    @Transactional
    @Modifying
    @Query(
            "UPDATE Plan p " +
            "SET p.isDeleted = TRUE, p.deletedAt = CURRENT_TIMESTAMP " +
            "WHERE p.member.id = :id " +
            "AND p.isDeleted IS NULL"
    )
    void softDeleteByMemberId (Long id);
}