package com.example.plan.member5.service;

import com.example.plan.config.PasswordEncoder;
import com.example.plan.member5.dto.response.LoginMemberResponseDto;
import com.example.plan.member5.dto.response.MemberResponseDto;
import com.example.plan.member5.entity.Member;
import com.example.plan.member5.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    // 속성
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 기능
     * 사용자 저장
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     * @param password : 사용자의 비밀번호
     * @return MemberResponseDto
     */
    @Transactional(readOnly = false)
    @Override
    public MemberResponseDto signUp(
            String username
            , String email
            , String password
    ) {
        String encodedPassword = passwordEncoder.encode(password);

        Member member = new Member(
                username
                , email
                , encodedPassword
        );

        Member savedMember = memberRepository.save(member);

        return MemberResponseDto.toDto(savedMember);
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
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED
                                , "이메일이 일치하지 않습니다."
                        )
                );

        boolean isPasswordDifferent = !passwordEncoder
                .matches(
                        password
                        , foundMember.getPassword()
                );

        if (isPasswordDifferent) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED
                    , "비밀번호가 일치하지 않습니다."
            );
        }
        return new LoginMemberResponseDto(foundMember.getId());
    }

    /**
     * 기능
     * 사용자 목록 찾기
     *
     * @return List<MemberResponseDto>
     */
    @Transactional(readOnly = true)
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
    @Transactional
    @Override
    public void delete(Long id) {
        int rowsAffected = memberRepository.softDelete(id);

        if(rowsAffected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
                    , "이미 삭제되었거나 존재하지 않는 id입니다."
            );
        }
    }
}