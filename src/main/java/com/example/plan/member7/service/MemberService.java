package com.example.plan.member7.service;

import com.example.plan.member7.dto.response.SignInMemberResponseDto;
import com.example.plan.member7.dto.response.MemberResponseDto;

import java.util.List;

public interface MemberService {

    /**
     * 기능
     * 사용자 저장
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     * @param password : 사용자의 비밀번호
     * @return MemberResponseDto
     */
    MemberResponseDto signUp(
            String username
            , String email
            , String password
    );

    /**
     * 기능
     * 사용자 로그인 처리, 즉 이메일과 비밀번호의 일치 여부 검증
     *
     * @param email    : 사용자가 로그인하려고 입력한 이메일
     * @param password : 사용자가 로그인하려고 입력한 비밀번호
     * @return LoginMemberResponseDto
     */
    SignInMemberResponseDto signIn(
            String email
            , String password
    );

    /**
     * 기능
     * 사용자 목록 조회
     *
     * @return List<MemberResponseDto>
     */
    List<MemberResponseDto> findAll();

    /**
     * 기능
     * [3/5] 식별자로 사용자 단건 조회
     *
     * @param id : 조회하려는 사용자의 식별자
     * @return MemberResponseDto
     */
    MemberResponseDto findById(Long id);

    /**
     * 기능
     * 사용자 단건 수정
     *
     * @param id       : 수정하려는 사용자의 식별자
     * @param username : 수정하려는 사용자의 이름
     * @param email    : 수정하려는 사용자의 이메일
     * @return MemberResponseDto
     */
    MemberResponseDto updateMember(
            Long id
            , String username
            , String email
    );

    /**
     * 기능
     * 사용자 단건 삭제
     *
     * @param id : 삭제하려는 사용자의 식별자
     */
    void delete(Long id);
}