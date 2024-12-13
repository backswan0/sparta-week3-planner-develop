package com.example.plan.plan.repository;

import com.example.plan.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 *
 *
 */

public interface PlanRepository extends JpaRepository<Plan, Long> {
    default Plan findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Id does not exist =" + id
                )
        );
    }
}