package com.example.plan.member7.service;

import com.example.plan.member7.dto.response.MemberResponseDto;
import com.example.plan.member7.dto.response.SignInMemberResponseDto;
import java.util.List;

public interface MemberService {

  MemberResponseDto signUp(
      String username,
      String email,
      String password
  );

  SignInMemberResponseDto signIn(
      String email,
      String password
  );

  List<MemberResponseDto> readAllMembers();

  MemberResponseDto readMemberById(Long memberId);

  MemberResponseDto updateMember(
      Long memberId,
      String username,
      String email
  );

  void deleteMember(Long memberId);
}