package com.example.plan.member7.service;

import com.example.plan.config.PasswordEncoder;
import com.example.plan.member7.dto.response.SignInMemberResponseDto;
import com.example.plan.member7.dto.response.MemberResponseDto;
import com.example.plan.member7.entity.Member;
import com.example.plan.member7.repository.MemberRepository;
import com.example.plan.note7.repository.NoteRepository;
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
    // 속성
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;
    private final NoteRepository noteRepository;
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
    @Transactional
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
    public SignInMemberResponseDto signIn(
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
        return new SignInMemberResponseDto(foundMember.getId());
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
        int rowsAffected = memberRepository.softDeleteById(id);

        if (rowsAffected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
                    , "이미 삭제되었거나 존재하지 않는 id입니다."
            );
        }
        // 해당 id의 사용자가 작성한 일정도 함께 소프트 딜리트 진행
        planRepository.softDeleteByMemberId(id);

        // 해당 id의 사용자가 작성한 댓글도 함께 소프트 딜리트 진행
        noteRepository.softDeleteByMemberId(id);
    }
}