package com.example.plan.member2.service;

import com.example.plan.member2.dto.response.MemberResponseDto;
import com.example.plan.member2.entity.Member;
import com.example.plan.member2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 유저 생성 완료
 * 유저 전체 조회 완료
 * 유저 단건 조회 완료
 * 유저 전체 수정 완료
 * 유저 단건 삭제 완료
 */

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDto signUp(
            String username
            , String email
    ) {
        Member member = new Member(username, email);

        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(
                savedMember.getId()
                , savedMember.getUsername()
                , savedMember.getEmail()
        );
    }

    @Override
    public List<MemberResponseDto> findAll() {

        List<MemberResponseDto> allMembers = new ArrayList<>();

        allMembers = memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();

        return allMembers;
    }

    @Override
    public MemberResponseDto findById(Long id) {

        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        return new MemberResponseDto(
                foundMember.getId()
                , foundMember.getUsername()
                , foundMember.getEmail()
        );
    }

    @Transactional
    @Override
    public MemberResponseDto updateMember(
            Long id
            , String username
            , String email
    ) {
        Member memberToUpdate = memberRepository.findByIdOrElseThrow(id);

        memberToUpdate.update(username, email);

        return new MemberResponseDto(
                memberToUpdate.getId()
                , memberToUpdate.getUsername()
                , memberToUpdate.getEmail()
        );
    }

    @Override
    public void delete(Long id) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        memberRepository.delete(foundMember);
    }
}