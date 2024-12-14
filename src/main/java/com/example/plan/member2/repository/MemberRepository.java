package com.example.plan.member2.repository;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 *
 *
 *
 */

import com.example.plan.member2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}