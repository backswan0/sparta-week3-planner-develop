package com.example.plan.plan2.repository;

import com.example.plan.plan2.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// update patch에서 사용자 이름 제외 리팩토링 완료

// [3/3 layers] 일정의 repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    /**
     * 기능
     * 일정을 식별자로 조회
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

/*
Q. EntityManager를 안 썼는데 왜 작동하지? 개발자
A. 챗GPT의 답변
EntityManager를 직접 사용하지 않고 JPA가 동작하는 상황이 있을 수 있다.
예를 들어, Spring Data JPA에선 CrudRepository, JpaRepository 같은 리포지토리 인터페이스를 사용하여 EntityManager를 추상화한 형태로 작업할 수 있다.
Spring Data JPA는 내부적으로 EntityManager를 사용하지만, 개발자가 직접 이를 다루지 않아도 된다.
 */