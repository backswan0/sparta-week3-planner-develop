package com.example.plan.plan.controller;

import com.example.plan.plan.dto.request.CreatePlanRequestDto;
import com.example.plan.plan.dto.request.UpdatePlanRequestDto;
import com.example.plan.plan.dto.response.PlanResponseDto;
import com.example.plan.plan.service.PlanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                requestDto.getUsername()
                , requestDto.getTitle()
                , requestDto.getTask()
        );
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAll() {
        List<PlanResponseDto> allPlans = planService.findAll();

        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findById(@PathVariable Long id) {
        PlanResponseDto responseDto = planService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updatePlan(
            @PathVariable Long id,
            @RequestBody UpdatePlanRequestDto requestDto
    ) {
        PlanResponseDto responseDto = planService.updatePlan(
                id
                , requestDto.getNewUsername()
                , requestDto.getNewTitle()
                , requestDto.getNewTask()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}