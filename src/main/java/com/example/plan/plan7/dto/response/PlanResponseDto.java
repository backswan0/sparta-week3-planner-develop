package com.example.plan.plan7.dto.response;

import com.example.plan.plan7.entity.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record PlanResponseDto(
    Long id,
    Long memberId,
    String title,
    String task,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt
) {

  public static PlanResponseDto toDto(Plan plan) {
    return new PlanResponseDto(
        plan.getId(),
        plan.getMember().getId(),
        plan.getTitle(),
        plan.getTask(),
        plan.getCreatedAt()
    );
  }
}