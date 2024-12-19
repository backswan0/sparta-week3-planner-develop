package com.example.plan.plan7.controller;

import com.example.plan.plan7.dto.request.CreatePlanRequestDto;
import com.example.plan.plan7.dto.request.UpdatePlanRequestDto;
import com.example.plan.plan7.dto.response.PlanReadResponseDto;
import com.example.plan.plan7.dto.response.PlanResponseDto;
import com.example.plan.plan7.service.PlanServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<PlanReadResponseDto>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        int pageNumber = pageable.getPageNumber();

        Pageable adjustedPageable = PageRequest.of(
                Math.max(pageNumber - 1, 0)
                , pageable.getPageSize()
                , Sort.by("updatedAt").descending()
        );
        List<PlanReadResponseDto> allPlans = planService.findAll(adjustedPageable);

        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }
    /*
    TODO
     클라이언트의 1과 컴퓨터의 1이 다르다는 점을 해설 특강에서 들어서 -1을 추가했다.
     또한 저 상태로 프로그램을 돌리면 0일 때 문제가 생겨서 코드를 수정해야 했다.
     코딩 테스트 연습 때 종종 마주친  Math.max(pageNumber - 1, 0)를 활용했다.
     앞으로는 좀 더 사용자와 클라이언트 관점에서 코드 개선점을 찾는 연습을 해야겠다.
     새로운 메서드를 쓸 때마다 문서를 보고 구조를 파악하는 일이 생각보다 재미있다.
     */

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