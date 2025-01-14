package com.example.plan.member7.repository;

import com.example.plan.member7.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  List<Member> findAllByIsDeletedFalse();

  Optional<Member> findByIdAndIsDeletedFalse(Long memberId);

  Optional<Member> findByEmail(String email);
}