package com.example.plan.plan5.service;

import com.example.plan.plan5.dto.response.PlanResponseDto;

import java.util.List;

public interface PlanService {

    /**
     * 기능
     * 일정 저장
     *
     * @param title  : 일정 제목
     * @param task   : 일정 내용
     * @param userId : 해당 일정을 작성한 사용자의 식별자
     * @return PlanResponseDto
     */
    PlanResponseDto save(
            String title
            , String task
            , Long userId
    );

    /**
     * 기능
     * 일정 목록 찾기
     *
     * @return List<PlanResponseDto>
     */
    List<PlanResponseDto> findAll();

    /**
     * 기능
     * 일정 단건을 id로 찾기
     *
     * @param id : 조회하려는 일정의 식별자
     * @return PlanResponseDto
     */
    PlanResponseDto findById(Long id);

    /**
     * 기능
     * 일정 단건 수정
     *
     * @param id    : 수정하려는 일정의 식별자
     * @param title : 수정하려는 일정의 제목
     * @param task  : 수정하려는 일정의 내용
     * @return PlanResponseDto
     */
    PlanResponseDto updatePlan(
            Long id
            , String title
            , String task
    );

    /**
     * 기능
     * 일정 단건 삭제
     *
     * @param id : 삭제하려는 일정의 식별자
     */
    void delete(Long id);
}