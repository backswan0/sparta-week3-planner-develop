package com.example.plan.plan7.controller;

import com.example.plan.plan7.dto.request.CreatePlanRequestDto;
import com.example.plan.plan7.dto.request.UpdatePlanRequestDto;
import com.example.plan.plan7.dto.response.PlanResponseDto;
import com.example.plan.plan7.dto.response.ReadPlanResponseDto;
import com.example.plan.plan7.service.PlanServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
      @Valid @RequestBody CreatePlanRequestDto requestDto
  ) {
    PlanResponseDto savedPlan = planService.createPlan(
        requestDto.title(),
        requestDto.task(),
        requestDto.memberId()
    );
    return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ReadPlanResponseDto>> readAllPlans(
      @PageableDefault(page = 0, size = 10)
      Pageable pageable
  ) {
    int pageNumber = pageable.getPageNumber();

    Pageable adjustedPageable = PageRequest.of(
        Math.max(pageNumber - 1, 0),
        pageable.getPageSize(),
        Sort.by("updatedAt").descending()
    );
    List<ReadPlanResponseDto> planList = planService
        .readAllPlans(adjustedPageable);

    return new ResponseEntity<>(planList, HttpStatus.OK);
  }

  @GetMapping("/{planId}")
  public ResponseEntity<PlanResponseDto> readPlanById(
      @PathVariable Long planId
  ) {
    PlanResponseDto responseDto = planService
        .readPlanById(planId);

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @PatchMapping("/{planId}")
  public ResponseEntity<PlanResponseDto> updatePlan(
      @PathVariable Long planId,
      @Valid @RequestBody UpdatePlanRequestDto requestDto
  ) {
    PlanResponseDto responseDto = planService
        .updatePlan(
            planId,
            requestDto.title(),
            requestDto.task()
        );
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{planId}")
  public ResponseEntity<Void> deletePlan(
      @PathVariable Long planId
  ) {
    planService.deletePlan(planId);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}