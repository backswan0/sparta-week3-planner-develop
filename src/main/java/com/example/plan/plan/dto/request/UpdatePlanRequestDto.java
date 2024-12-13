package com.example.plan.plan.dto.request;

import lombok.Getter;

@Getter
public class UpdatePlanRequestDto {
    private final String newUsername;
    private final String newTitle;
    private final String newTask;

    public UpdatePlanRequestDto(
            String newUsername
            , String newTitle
            , String newTask
    ) {
        this.newUsername = newUsername;
        this.newTitle = newTitle;
        this.newTask = newTask;
    }
}