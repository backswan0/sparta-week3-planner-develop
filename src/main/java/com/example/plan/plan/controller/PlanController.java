package com.example.plan.plan.controller;

import com.example.plan.plan.dto.request.CreatePlanRequestDto;
import com.example.plan.plan.dto.response.PlanResponseDto;
import com.example.plan.plan.service.PlanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}