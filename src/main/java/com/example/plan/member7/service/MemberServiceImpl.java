package com.example.plan.member7.service;

import com.example.plan.base.BaseEntity;
import com.example.plan.comment7.entity.Comments;
import com.example.plan.comment7.repository.CommentRepository;
import com.example.plan.config.PasswordEncoder;
import com.example.plan.exception.AlreadyDeletedException;
import com.example.plan.exception.mismatch.EmailMismatchException;
import com.example.plan.exception.mismatch.PasswordMismatchException;
import com.example.plan.exception.notfound.MemberNotFoundException;
import com.example.plan.member7.dto.response.MemberResponseDto;
import com.example.plan.member7.dto.response.SignInMemberResponseDto;
import com.example.plan.member7.entity.Member;
import com.example.plan.member7.repository.MemberRepository;
import com.example.plan.plan7.entity.Plan;
import com.example.plan.plan7.repository.PlanRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final PlanRepository planRepository;
  private final CommentRepository commentRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public MemberResponseDto signUp(
      String username,
      String email,
      String password
  ) {
    String encodedPassword = passwordEncoder.encode(password);

    Member member = new Member(
        username,
        email,
        encodedPassword
    );

    Member savedMember = memberRepository.save(member);

    return MemberResponseDto.toDto(savedMember);
  }

  @Transactional
  @Override
  public SignInMemberResponseDto signIn(
      String email,
      String password
  ) {
    Member foundMember = findMemberByEmail(email);

    boolean isPasswordDifferent = !passwordEncoder.matches(
        password,
        foundMember.getPassword()
    );

    if (isPasswordDifferent) {
      throw new PasswordMismatchException();
    }

    return new SignInMemberResponseDto(foundMember.getId());
  }

  @Transactional(readOnly = true)
  @Override
  public List<MemberResponseDto> readAllMembers() {

    List<MemberResponseDto> memberList = new ArrayList<>();

    memberList = memberRepository.findAllByIsDeletedFalse()
        .stream()
        .map(MemberResponseDto::toDto)
        .toList();

    return memberList;
  }

  @Transactional(readOnly = true)
  @Override
  public MemberResponseDto readMemberById(Long memberId) {

    Member foundMember = findMemberById(memberId);

    return MemberResponseDto.toDto(foundMember);
  }

  @Transactional
  @Override
  public MemberResponseDto updateMember(
      Long memberId,
      String username,
      String email
  ) {
    Member foundMember = findMemberById(memberId);

    foundMember.update(username, email);

    return MemberResponseDto.toDto(foundMember);
  }

  @Transactional
  @Override
  public void deleteMember(Long memberId) {
    Member foundMember = findMemberById(memberId);

    if (foundMember.getIsDeleted()) {
      throw new AlreadyDeletedException();
    }

    foundMember.markAsDeleted();

    List<Plan> planList = new ArrayList<>();

    planList = planRepository.findAllByMemberIdAndIsDeletedFalse(memberId);

    planList.stream()
        .peek(BaseEntity::markAsDeleted)
        .forEach(plan -> {
              List<Comments> commentsList = commentRepository
                  .findAllByPlanIdAndIsDeletedFalse(
                      plan.getId()
                  );
              commentsList.forEach(BaseEntity::markAsDeleted);
            }
        );
  }

  private Member findMemberById(Long memberId) {
    return memberRepository.findByIdAndIsDeletedFalse(memberId)
        .orElseThrow(
            MemberNotFoundException::new
        );
  }

  private Member findMemberByEmail(String email) {
    return memberRepository.findByEmail(email)
        .orElseThrow(
            EmailMismatchException::new
        );
  }
}