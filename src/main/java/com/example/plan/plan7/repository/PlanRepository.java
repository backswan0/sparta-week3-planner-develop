package com.example.plan.plan7.repository;

import com.example.plan.plan7.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Page<Plan> findAllByIsDeletedFalse(Pageable pageable);

    Optional<Plan> findByIdAndIsDeletedFalse(Long planId);

    List<Plan> findAllByMemberIdAndIsDeletedFalse(Long memberId);
}