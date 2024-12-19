package com.example.plan.member2.service;

import com.example.plan.member2.dto.response.MemberResponseDto;
import com.example.plan.member2.entity.Member;
import com.example.plan.member2.repository.MemberRepository;
import com.example.plan.plan2.repository.PlanRepository;
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

    /**
     * 기능
     * [1/5] 사용자 저장
     *
     * @param username : 사용자의 이름
     * @param email    : 사용자의 이메일
     * @return MemberResponseDto
     */
    @Transactional(readOnly = false) // 기본값이지만, 학습용으로 써둠
    @Override
    public MemberResponseDto signUp(
            String username
            , String email
    ) {
        Member member = new Member(username, email);

        Member savedMember = memberRepository.save(member);

        return MemberResponseDto.toDto(savedMember);
    }

    /**
     * 기능
     * [2/5] 사용자 목록 찾기
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

        allMembers = memberRepository.findAllExceptDeleted()
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
    @Transactional(readOnly = true)
    @Override
    public MemberResponseDto findById(Long id) {

        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        return MemberResponseDto.toDto(foundMember);
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

        return MemberResponseDto.toDto(memberToUpdate);
    }

    /**
     * 기능
     * [5/5] 사용자 단건 삭제
     *
     * @param id : 삭제하려는 사용자의 식별자
     */
    @Transactional(readOnly = false)
    @Override
    public void delete(Long id) {
        int rowsAffected = memberRepository.softDeleteById(id);

        if (rowsAffected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
                    , "이미 삭제되었거나 존재하지 않는 id입니다."
            );
        }
        // 삭제된 사용자가 작성한 일정도 함께 삭제
        planRepository.softDeleteByMemberId(id);
    }
}