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

// [1/3 layers] 일정의 controller
@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {
    // 속성
    private final PlanServiceImpl planService;

    /**
     * 기능
     * [1/5] CREATE - 일정 생성
     *
     * @param requestDto : CreatePlanRequestDto
     * @return PlanResponseDto, HttpStatus 201 CREATED
     */
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

    /**
     * 기능
     * [2/5] READ - 일정 목록 조회
     *
     * @return List<PlanResponseDto>, HttpStatus 200 OK
     */
    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAll() {
        List<PlanResponseDto> allPlans = planService.findAll();

        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    /**
     * 기능
     * [3/5] READ - 일정 단건 조회
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
     * [4/5] UPDATE (PATCH) - 일정 수정
     *
     * @param id         : 수정하려는 일정의 식별자
     * @param requestDto : UpdatePlanRequestDto. 일정 제목과 일정 내용 포함
     * @return PlanResponseDto, HttpStatus 200 OK
     */
    @PatchMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updatePlan(
            @PathVariable Long id,
            @RequestBody UpdatePlanRequestDto requestDto
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
     * [5/5] DELETE - 일정 단건 삭제
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