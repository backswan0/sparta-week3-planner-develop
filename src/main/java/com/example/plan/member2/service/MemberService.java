package com.example.plan.member2.service;

import com.example.plan.member2.dto.response.MemberResponseDto;

public interface MemberService {

    MemberResponseDto signUp(
            String username
            , String email
    );
}