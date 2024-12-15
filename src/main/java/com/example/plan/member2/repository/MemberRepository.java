package com.example.plan.member2.repository;

import com.example.plan.member2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 기능
     * 사용자를 id로 찾아오고 id가 없을 시 예외 처리
     * @param id : 가져오려는 사용자의 식별자
     * @return Member
     */
    default Member findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                        , "Id does not exist. Input id = " + id
                )
        );
    }
}