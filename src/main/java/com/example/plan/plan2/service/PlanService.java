package com.example.plan.plan2.service;

import com.example.plan.plan2.dto.response.PlanResponseDto;

import java.util.List;

// update patch에서 사용자 이름 제외 리팩토링 완료

// 일정 service layer의 인터페이스
public interface PlanService {

    /**
     * 기능
     * [1/5] 일정 저장
     *
     * @param username : 작성자 이름
     * @param title    : 일정 제목
     * @param task     : 일정 내용
     * @return PlanResponseDto
     */
    PlanResponseDto save(
            String username
            , String title
            , String task
    );

    /**
     * 기능
     * [2/5] 일정 목록 찾기
     *
     * @return List<PlanResponseDto>
     */
    List<PlanResponseDto> findAll();

    /**
     * 기능
     * [3/5] 일정 단건을 id로 찾기
     *
     * @param id : 조회하려는 일정의 식별자
     * @return PlanResponseDto
     */
    PlanResponseDto findById(Long id);

    /**
     * 기능
     * [4/5] 일정 단건 수정
     *
     * @param id    : 수정하려는 일정의 식별자
     * @param title : 수정하려는 일정 제목
     * @param task  : 수정하려는 일정 내용
     * @return PlanResponseDto
     */
    PlanResponseDto updatePlan(
            Long id
            , String title
            , String task
    );

    /**
     * 기능
     * [5/5] 일정 단건 삭제
     *
     * @param id : 삭제하려는 일정의 식별자
     */
    void delete(Long id);
}