package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;
import com.example.plan.plan.entity.Plan;
import com.example.plan.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

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

    @Override
    public List<PlanResponseDto> findAll() {

        List<PlanResponseDto> allPlans = new ArrayList<>();

        allPlans = planRepository.findAll()
                .stream()
                .map(PlanResponseDto::toDto)
                .toList();

        return allPlans;
    }

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

    @Override
    public void delete(Long id) {
        Plan foundPlan = planRepository.findByIdOrElseThrow(id);

        planRepository.delete(foundPlan);
    }
}