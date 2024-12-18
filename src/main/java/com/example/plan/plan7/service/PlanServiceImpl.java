package com.example.plan.plan7.service;

import com.example.plan.member7.entity.Member;
import com.example.plan.member7.repository.MemberRepository;
import com.example.plan.comment7.repository.CommentRepository;
import com.example.plan.plan7.dto.response.PlanReadResponseDto;
import com.example.plan.plan7.dto.response.PlanResponseDto;
import com.example.plan.plan7.entity.Plan;
import com.example.plan.plan7.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    // 속성
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    /**
     * 기능
     * 일정 생성
     *
     * @param title  : 일정 제목
     * @param task   : 일정 내용
     * @param userId : 해당 일정을 작성한 사용자의 식별자
     * @return PlanResponseDto
     */
    @Transactional
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
    public List<PlanReadResponseDto> findAll(Pageable pageable) {

        List<PlanReadResponseDto> planList = new ArrayList<>();

        planList = planRepository.findAllExceptDeleted(pageable)
                .getContent()
                .stream()
                .map(plan -> {
                    int totalComment = commentRepository.countByPlanId(plan.getId());

                    return PlanReadResponseDto.toDto(plan, totalComment);
                }).toList();

        return planList;
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
        int rowsAffected = planRepository.softDeleteById(id);

        if (rowsAffected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
                    , "이미 삭제되었거나 존재하지 않는 id입니다."
            );
        }

        // 일정이 삭제될 때 해당 일정에 있는 댓글도 모두 소프트 딜리트 진행
        commentRepository.softDeleteByPlanId(id);
    }
}