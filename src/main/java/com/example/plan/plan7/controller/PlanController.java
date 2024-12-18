package com.example.plan.plan7.controller;

import com.example.plan.plan7.dto.request.CreatePlanRequestDto;
import com.example.plan.plan7.dto.request.UpdatePlanRequestDto;
import com.example.plan.plan7.dto.response.PlanResponseDto;
import com.example.plan.plan7.service.PlanServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {
    // 속성
    private final PlanServiceImpl planService;

    /**
     * 기능
     * CREATE - 일정 생성
     *
     * @param requestDto : CreatePlanRequestDto
     * @return PlanResponseDto, HttpStatus 201 CREATED
     */
    @PostMapping
    public ResponseEntity<PlanResponseDto> save(
            @Valid @RequestBody CreatePlanRequestDto requestDto
    ) {
        PlanResponseDto savedPlan = planService.save(
                requestDto.getTitle()
                , requestDto.getTask()
                , requestDto.getUserId()
        );
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    /**
     * 기능
     * READ - 일정 목록 조회
     *
     * @return List<PlanResponseDto>, HttpStatus 200 OK
     */
    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAll() {
        List<PlanResponseDto> allPlans = new ArrayList<>();

        allPlans = planService.findAll();

        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    /**
     * 기능
     * READ - 일정 단건 조회
     *
     * @param id : 조회하려는 일정의 식별자
     * @return PlanResponseDto, HttpStatus 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findById(@PathVariable Long id) {
        PlanResponseDto responseDto = planService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 기능
     * UPDATE (PATCH) - 일정 수정
     *
     * @param id         : 수정하려는 일정의 식별자
     * @param requestDto : UpdatePlanRequestDto
     * @return PlanResponseDto, HttpStatus 200 OK
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updatePlan(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePlanRequestDto requestDto
    ) {
        PlanResponseDto responseDto = planService.updatePlan(
                id
                , requestDto.getTitle()
                , requestDto.getTask()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 기능
     * DELETE - 일정 단건 삭제
     *
     * @param id : 삭제하려는 일정의 식별자
     * @return HttpStatus 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}