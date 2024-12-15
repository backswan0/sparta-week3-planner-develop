package com.example.plan.member2.service;

import com.example.plan.member2.dto.response.MemberResponseDto;

import java.util.List;

public interface MemberService {

    MemberResponseDto signUp(
            String username
            , String email
    );

    List<MemberResponseDto> findAll();

    MemberResponseDto findById(Long id);

    MemberResponseDto updateMember(
            Long id
            , String username
            , String email
    );

    void delete(Long id);
}