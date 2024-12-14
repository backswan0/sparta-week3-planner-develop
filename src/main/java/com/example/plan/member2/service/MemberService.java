package com.example.plan.member2.service;

import com.example.plan.member2.dto.response.MemberResponseDto;

import java.util.List;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 * 유저 단건 조회 완료
 *
 *
 */

public interface MemberService {

    MemberResponseDto signUp(
            String username
            , String email
    );

    List<MemberResponseDto> findAll();

    MemberResponseDto findById(Long id);
}