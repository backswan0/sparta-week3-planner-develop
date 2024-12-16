package com.example.plan.plan2.repository;

import com.example.plan.plan2.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
                        , "Id does not exist. Input id = " + id
                )
        );
    }
}