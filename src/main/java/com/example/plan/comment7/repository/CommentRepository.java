package com.example.plan.comment7.repository;

import com.example.plan.comment7.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findAllByIsDeletedFalse();

    Optional<Comments> findByIdAndIsDeletedFalse(Long commentId);

    int countByPlanId(Long planId);

    @Transactional
    @Modifying
    @Query(
            "UPDATE Comments c " +
                    "SET c.isDeleted = TRUE, c.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE c.plan.id = :id " +
                    "AND c.isDeleted IS NULL"
    )
    void softDeleteByPlanId(Long id);

    @Transactional
    @Modifying
    @Query(
            "UPDATE Comments c " +
                    "SET c.isDeleted = TRUE, c.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE c.member.id = :id " +
                    "AND c.isDeleted IS NULL"
    )
    void softDeleteByMemberId(Long id);
}