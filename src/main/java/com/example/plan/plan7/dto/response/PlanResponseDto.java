package com.example.plan.plan7.dto.response;

import com.example.plan.member7.entity.Member;
import com.example.plan.plan7.entity.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PlanResponseDto(
        Long id,
        String title,
        String task,
        Member member,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt
) {

    public static PlanResponseDto toDto(Plan plan) {
        return new PlanResponseDto(
                plan.getId()
                , plan.getTitle()
                , plan.getTask()
                , plan.getMember()
                , plan.getCreatedAt()
                , plan.getUpdatedAt()
        );
    }
}