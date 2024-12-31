package com.example.plan.plan7.repository;

import com.example.plan.plan7.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Page<Plan> findAllByIsDeletedFalse(Pageable pageable);

    Optional<Plan> findByIdAndIsDeletedFalse(Long planId);

    @Transactional
    @Modifying
    @Query(
            "UPDATE Plan p " +
                    "SET p.isDeleted = TRUE, p.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE p.member.id = :id " +
                    "AND p.isDeleted IS NULL"
    )
    void softDeleteByMemberId(Long id);
}