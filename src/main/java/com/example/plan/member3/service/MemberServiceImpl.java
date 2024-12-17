package com.example.plan.member3.service;

import com.example.plan.member3.dto.response.LoginMemberResponseDto;
import com.example.plan.member3.dto.response.MemberResponseDto;
import com.example.plan.member3.entity.Member;
import com.example.plan.member3.repository.MemberRepository;
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
    // 속성
    private final MemberRepository memberRepository;

    /**
     * 기능
     * 사용자 저장
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     * @return MemberResponseDto
     */
    @Transactional(readOnly = false)
    @Override
    public MemberResponseDto signUp(
            String username
            , String email
            , String password
    ) {
        Member member = new Member(
                username
                , email
                , password
        );

        Member savedMember = memberRepository.save(member);

        return MemberResponseDto.toDto(savedMember);
    }

    /**
     * 기능
     * 사용자 목록 찾기
     *
     * @return List<MemberResponseDto>
     */
    @Transactional(readOnly = true)
    // 불필요한 스냅샷을 찍지 않으려고
    // (스냅샷: 변경 확인하려고, 근데 조회할 땐 변경할 사항이 있는지 확인할 이유가 없으니까)
    // 외부에서 변경이 이루어져도 디비에서 이루어지지 않음
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
     * 사용자 단건 조회
     *
     * @param id : 조회하려는 사용자의 식별자
     * @return MemberResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public MemberResponseDto findById(Long id) {

        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        return MemberResponseDto.toDto(foundMember);
    }

    /**
     * 기능
     * 사용자 단건 수정
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
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        foundMember.update(username, email);

        return MemberResponseDto.toDto(foundMember);
    }

    /**
     * 기능
     * 사용자 단건 삭제
     *
     * @param id : 삭제하려는 사용자의 식별자
     */
    @Transactional(readOnly = false)
    @Override
    public void delete(Long id) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        memberRepository.delete(foundMember);
    }

    /**
     * 기능
     * 사용자 로그인 처리, 즉 이메일과 비밀번호의 일치 여부 검증
     *
     * @param email    : 사용자가 로그인하려고 입력한 이메일
     * @param password : 사용자가 로그인하려고 입력한 비밀번호
     * @return LoginMemberResponseDto
     */
    @Override
    public LoginMemberResponseDto login(
            String email
            , String password
    ) {
        Member foundMember = memberRepository.findByEmailAndPassword(
                        email
                        , password
                )
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED
                                , "Email or Password is incorrect. Please try again."
                        )
                );
        return new LoginMemberResponseDto(foundMember.getId());
    }
}