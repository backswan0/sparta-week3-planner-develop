package com.example.plan.member2.service;

import com.example.plan.member2.dto.response.MemberResponseDto;
import com.example.plan.member2.entity.Member;
import com.example.plan.member2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    // 속성
    private final MemberRepository memberRepository;

    /**
     * 기능
     * [1/5] 사용자 저장
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     * @return MemberResponseDto
     */
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

    /**
     * 기능
     * [2/5] 사용자 목록 찾기
     *
     * @return List<MemberResponseDto>
     */
    @Override
    public List<MemberResponseDto> findAll() {

        List<MemberResponseDto> allMembers = new ArrayList<>();

        allMembers = memberRepository.findAll()
                .stream()
                .map(MemberResponseDto::toDto)
                .toList();

        return allMembers;
    }

    /**
     * 기능
     * [3/5] 사용자 단건 조회
     *
     * @param id : 조회하려는 사용자의 식별자
     * @return MemberResponseDto
     */
    @Override
    public MemberResponseDto findById(Long id) {

        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        return new MemberResponseDto(
                foundMember.getId()
                , foundMember.getUsername()
                , foundMember.getEmail()
        );
    }

    /**
     * 기능
     * [4/5] 사용자 단건 수정
     *
     * @param id       : 수정하려는 사용자의 식별자
     * @param username : 수정하려는 사용자의 이름
     * @param email    : 수정하려는 사용자의 이메일
     * @return MemberResponseDto
     */
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

    /**
     * 기능
     * [5/5] 사용자 단건 삭제
     *
     * @param id : 삭제하려는 사용자의 식별자
     */
    @Override
    public void delete(Long id) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        memberRepository.delete(foundMember);
    }
}