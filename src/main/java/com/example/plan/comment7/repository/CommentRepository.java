package com.example.plan.comment7.repository;

import com.example.plan.comment7.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findAllByIsDeletedFalse();

    Optional<Comments> findByIdAndIsDeletedFalse(Long commentId);

    int countByPlanId(Long planId);

    List<Comments> findAllByPlanIdAndIsDeletedFalse(Long planId);
}