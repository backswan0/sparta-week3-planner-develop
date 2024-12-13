package com.example.plan.plan.controller;

import com.example.plan.plan.dto.request.CreatePlanRequestDto;
import com.example.plan.plan.dto.response.PlanResponseDto;
import com.example.plan.plan.service.PlanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 생성 완료
 * 전체 조회 완료
 * 단건 조회 완료
 *
 *
 */

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanServiceImpl planService;

    @PostMapping
    public ResponseEntity<PlanResponseDto> save(
            @RequestBody CreatePlanRequestDto requestDto
    ) {
        PlanResponseDto savedPlan = planService.save(
                requestDto.getUsername(),
                requestDto.getTitle(),
                requestDto.getTask()
        );
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAll() {
        List<PlanResponseDto> allPlans = planService.findAll();

        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findById(
            @PathVariable Long id
    ) {
        PlanResponseDto responseDto = planService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}