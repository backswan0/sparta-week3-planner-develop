package com.example.plan.plan5.service;

import com.example.plan.member5.entity.Member;
import com.example.plan.member5.repository.MemberRepository;
import com.example.plan.plan5.dto.response.PlanResponseDto;
import com.example.plan.plan5.entity.Plan;
import com.example.plan.plan5.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    // 속성
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;

    /**
     * 기능
     * 일정 생성
     *
     * @param title  : 일정 제목
     * @param task   : 일정 내용
     * @param userId : 해당 일정을 작성한 사용자의 식별자
     * @return PlanResponseDto
     */
    @Transactional(readOnly = false)
    @Override
    public PlanResponseDto save(
            String title
            , String task
            , Long userId
    ) {
        Member foundMember = memberRepository.findByIdOrElseThrow(userId);

        Plan planToSave = new Plan(
                title
                , task
        );

        planToSave.setMember(foundMember);

        Plan savedPlan = planRepository.save(planToSave);

        return PlanResponseDto.toDto(savedPlan);
    }

    /**
     * 기능
     * 일정 목록 찾기
     *
     * @return List<PlanResponseDto>
     */
    @Transactional(readOnly = true)
    @Override
    public List<PlanResponseDto> findAll() {

        List<PlanResponseDto> allPlans = new ArrayList<>();

        allPlans = planRepository.findAll()
                .stream()
                .map(PlanResponseDto::toDto)
                .toList();

        return allPlans;
    }

    /**
     * 기능
     * 일정 단건을 id로 찾기
     *
     * @param id : 조회하려는 일정의 식별자
     * @return PlanResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public PlanResponseDto findById(Long id) {

        Plan foundPlan = planRepository.findByIdOrElseThrow(id);

        return PlanResponseDto.toDto(foundPlan);
    }

    /**
     * 기능
     * 일정 단건 수정 (UPDATE - PATCH: 작성자 이름을 제외했으므로 부분 수정)
     *
     * @param id    : 수정하려는 일정의 식별자
     * @param title : 수정하려는 일정 제목
     * @param task  : 수정하려는 일정 내용
     * @return PlanResponseDto
     */
    @Override
    public PlanResponseDto updatePlan(
            Long id
            , String title
            , String task
    ) {
        Plan planToUpdate = planRepository.findByIdOrElseThrow(id);

        planToUpdate.update(
                title
                , task
        );

        Plan updatedPlan = planRepository.save(planToUpdate);

        return PlanResponseDto.toDto(updatedPlan);
    }

    /**
     * 기능
     * 일정 단건 삭제
     *
     * @param id : 삭제하려는 일정의 식별자
     */
    @Override
    public void delete(Long id) {
        Plan foundPlan = planRepository.findByIdOrElseThrow(id);

        planRepository.delete(foundPlan);
    }
}