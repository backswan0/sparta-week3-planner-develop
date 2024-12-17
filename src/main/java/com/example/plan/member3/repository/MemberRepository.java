package com.example.plan.member3.repository;

import com.example.plan.member3.dto.response.LoginMemberResponseDto;
import com.example.plan.member3.dto.response.MemberResponseDto;
import com.example.plan.member3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 기능
     * 사용자를 식별자(id)로 찾아오고 id가 없을 시 예외 처리
     *
     * @param id : 조회하려는 사용자의 식별자
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

    /**
     * 이메일과 비밀번호로 사용자 조회
     *
     * @param email    : 사용자가 입력한 이메일
     * @param password : 사용자가 입력한 비밀번호
     * @return Optional<Member>
     */
    Optional<Member> findByEmailAndPassword(
            String email
            , String password
    );

    /**
     * 이메일과 비밀번호로 사용자 조회 및 예외 처리
     *
     * @param email    : 사용자의 이메일
     * @param password : 사용자의 비밀번호
     * @return Member
     */
    default Member findByEmailAndPasswordOrElseThrow(
            String email
            , String password
    ) {
        return findByEmailAndPassword(email, password).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                        , "Email or Password does not match. Please try again."
                )
        );
    }
}