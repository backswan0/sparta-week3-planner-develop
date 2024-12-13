package com.example.plan.plan.service;

import com.example.plan.plan.dto.response.PlanResponseDto;
import com.example.plan.plan.entity.Plan;
import com.example.plan.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 *
 *
 */

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public PlanResponseDto save(
            String username,
            String title,
            String task
    ) {
        Plan planToSave = new Plan(
                username,
                title,
                task
        );

        Plan savedPlan = planRepository.save(planToSave);

        return new PlanResponseDto(
                savedPlan.getId(),
                savedPlan.getUsername(),
                savedPlan.getTitle(),
                savedPlan.getTask(),
                savedPlan.getCreatedAt(),
                savedPlan.getUpdatedAt()
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
                foundPlan.getId(),
                foundPlan.getUsername(),
                foundPlan.getTitle(),
                foundPlan.getTask(),
                foundPlan.getCreatedAt(),
                foundPlan.getUpdatedAt()
        );
    }
}