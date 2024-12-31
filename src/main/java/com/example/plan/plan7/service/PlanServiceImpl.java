package com.example.plan.plan7.service;

import com.example.plan.base.BaseEntity;
import com.example.plan.comment7.entity.Comments;
import com.example.plan.member7.entity.Member;
import com.example.plan.member7.repository.MemberRepository;
import com.example.plan.comment7.repository.CommentRepository;
import com.example.plan.plan7.dto.response.*;
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
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public PlanResponseDto createPlan(
            String title,
            String task,
            Long memberId
    ) {
        Member foundMember = memberRepository
                .findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                ); // todo

        Plan planToSave = new Plan(title, task);

        planToSave.updateMember(foundMember);

        Plan savedPlan = planRepository.save(planToSave);

        return PlanResponseDto.toDto(savedPlan);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReadPlanResponseDto> readAllPlans(Pageable pageable) {

        List<ReadPlanResponseDto> planList = new ArrayList<>();

        planList = planRepository
                .findAllByIsDeletedFalse(pageable)
                .getContent()
                .stream()
                .map(plan -> {
                            int totalComment = commentRepository
                                    .countByPlanId(plan.getId());
                            return ReadPlanResponseDto
                                    .toDto(plan, totalComment);
                        }
                ).toList();

        return planList;
    }

    @Transactional(readOnly = true)
    @Override
    public PlanResponseDto readPlanById(Long planId) {

        Plan foundPlan = findPlanById(planId);

        return PlanResponseDto.toDto(foundPlan);
    }

    @Transactional
    @Override
    public PlanResponseDto updatePlan(
            Long planId,
            String title,
            String task
    ) {
        Plan foundPlan = findPlanById(planId);

        foundPlan.updatePlan(title, task);

        return PlanResponseDto.toDto(foundPlan);
    }

    @Transactional
    @Override
    public void deletePlan(Long planId) {
        Plan foundPlan = findPlanById(planId);

        if (foundPlan.getIsDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "The requested data has already been deleted"
            );
        } // todo

        foundPlan.markAsDeleted();

        List<Comments> commentList = new ArrayList<>();

        commentList = commentRepository
                .findAllByPlanIdAndIsDeletedFalse(planId);

        commentList.stream()
                .peek(BaseEntity::markAsDeleted)
                .forEach(comments -> {
                        }
                );
    }

    private Plan findPlanById(Long planId) {
        return planRepository
                .findByIdAndIsDeletedFalse(planId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                );
    } // todo
}