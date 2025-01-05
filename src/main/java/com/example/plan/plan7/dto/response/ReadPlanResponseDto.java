package com.example.plan.plan7.dto.response;

import com.example.plan.plan7.entity.Plan;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ReadPlanResponseDto(
    String title,
    String task,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime updatedAt,

    String username,
    int totalComment
) {

  public static ReadPlanResponseDto toDto(
      Plan plan,
      int totalComment
  ) {
    return new ReadPlanResponseDto(
        plan.getTitle(),
        plan.getTask(),
        plan.getCreatedAt(),
        plan.getUpdatedAt(),
        plan.getMember().getUsername(),
        totalComment
    );
  }
}