package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;
import com.example.plan.plan.entity.Plan;
import com.example.plan.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// [2/3 layers] 일정의 service. PlanService 인터페이스를 오버라이딩했다.
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    // 속성
    private final PlanRepository planRepository;

    /**
     * 기능
     * [1/5] 일정 저장
     *
     * @param username : 작성자 이름
     * @param title    : 일정 제목
     * @param task     : 일정 내용
     * @return PlanResponseDto
     */
    @Override
    public PlanResponseDto save(
            String username
            , String title
            , String task
    ) {
        Plan planToSave = new Plan(
                username
                , title
                , task
        );

        Plan savedPlan = planRepository.save(planToSave);

        return new PlanResponseDto(
                savedPlan.getId()
                , savedPlan.getUsername()
                , savedPlan.getTitle()
                , savedPlan.getTask()
        );
    }

    /**
     * 기능
     * [2/5] 일정 목록 찾기
     *
     * @return List<PlanResponseDto>
     */
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
     * [3/5] 일정 단건을 id로 찾기
     *
     * @param id : 조회하려는 일정의 식별자
     * @return PlanResponseDto
     */
    @Override
    public PlanResponseDto findById(Long id) {

        Plan foundPlan = planRepository.findByIdOrElseThrow(id);

        return new PlanResponseDto(
                foundPlan.getId()
                , foundPlan.getUsername()
                , foundPlan.getTitle()
                , foundPlan.getTask()
        );
    }

    /**
     * 기능
     * [4/5] 일정 단건 수정
     *
     * @param id          : 수정하려는 일정의 식별자
     * @param newUsername : 수정하려는 작성자 이름
     * @param newTitle    : 수정하려는 일정 제목
     * @param newTask     : 수정하려는 일정 내용
     * @return PlanResponseDto
     */
    @Transactional
    @Override
    public PlanResponseDto updatePlan(
            Long id
            , String newUsername
            , String newTitle
            , String newTask
    ) {
        Plan planToUpdate = planRepository.findByIdOrElseThrow(id);

        planToUpdate.update(
                newUsername
                , newTitle
                , newTask
        );

        return new PlanResponseDto(
                planToUpdate.getId()
                , planToUpdate.getUsername()
                , planToUpdate.getTitle()
                , planToUpdate.getTask()
        );
    }

    /**
     * 기능
     * [5/5] 일정 단건 삭제
     *
     * @param id : 삭제하려는 일정의 식별자
     */
    @Override
    public void delete(Long id) {
        Plan foundPlan = planRepository.findByIdOrElseThrow(id);

        planRepository.delete(foundPlan);
    }
}