package com.example.plan.member7.repository;

import com.example.plan.member7.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 기능
     * 소프트 딜리트가 된 사용자를 제외한 목록 조회
     *
     * @return List<Member>
     */
    @Query("SELECT m FROM Member m WHERE m.isDeleted IS NULL")
    List<Member> findAllExceptDeleted();

    /**
     * 기능
     * 소프트 딜리트가 된 사용자를 제외한 단건 조회용 메서드
     *
     * @param id : 조회하려는 사용자의 식별자
     * @return Optional<Member>
     */
    @Query("SELECT m FROM Member m WHERE m.id = :id AND m.isDeleted IS NULL")
    Optional<Member> findByIdExceptDeleted(Long id);

    /**
     * 기능
     * 사용자를 식별자(id)로 찾고 없을 시 예외 처리
     *
     * @param id : 조회하려는 사용자의 식별자
     * @return Member
     */
    default Member findByIdOrElseThrow(Long id) {
        return findByIdExceptDeleted(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                        , "입력된 id가 존재하지 않습니다. 다시 입력해 주세요."
                )
        );
    }

    /**
     * 기능
     * 이메일로 사용자 조회
     *
     * @param email : 사용자가 입력한 이메일
     * @return Optional<Member>
     */
    Optional<Member> findByEmail(String email);

    /**
     * 기능
     * 사용자 소프트 딜리트(soft delete)
     *
     * @param id : 소프트 딜리트를 하려고 하는 일정
     * @return int
     */
    @Transactional
    @Modifying
    @Query(
            "UPDATE Member m " +
                    "SET m.isDeleted = TRUE, m.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE m.id = :id " +
                    "AND m.isDeleted IS NULL"
    )
    int softDeleteById(Long id);
}