package com.example.plan.member7.service;

import com.example.plan.base.BaseEntity;
import com.example.plan.comment7.entity.Comments;
import com.example.plan.comment7.repository.CommentRepository;
import com.example.plan.config.PasswordEncoder;
import com.example.plan.member7.dto.response.*;
import com.example.plan.member7.entity.Member;
import com.example.plan.member7.repository.*;
import com.example.plan.plan7.entity.Plan;
import com.example.plan.plan7.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED
                                , "Email does not Match"
                        )
                ); // todo

        boolean isPasswordDifferent = !passwordEncoder
                .matches(
                        password
                        , foundMember.getPassword()
                );

        if (isPasswordDifferent) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED
                    , "Password does not match"
            );
        } // todo

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

        Member foundMember = memberRepository
                .findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                ); // todo

        return MemberResponseDto.toDto(foundMember);
    }

    @Transactional
    @Override
    public MemberResponseDto updateMember(
            Long memberId,
            String username,
            String email
    ) {
        Member foundMember = memberRepository
                .findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                ); // todo

        foundMember.update(username, email);

        return MemberResponseDto.toDto(foundMember);
    }

    @Transactional
    @Override
    public void deleteMember(Long memberId) {
        Member foundMember = memberRepository
                .findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id doest not exist"
                        )
                ); // todo

        if (foundMember.getIsDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "The requested data has already been deleted"
            );
        } // todo

        foundMember.markAsDeleted();

        List<Plan> planList = new ArrayList<>();

        planList = planRepository
                .findAllByMemberIdAndIsDeletedFalse(memberId);

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
}