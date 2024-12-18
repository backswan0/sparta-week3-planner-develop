package com.example.plan.plan5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdatePlanRequestDto {
    // 속성
    @NotBlank(message = "일정의 제목 입력은 필수입니다.")
    @Length(min = 1, max = 20)
    private final String title;

    @NotEmpty(message = "null과 빈값을 허용하지 않습니다. 공백으로 입력해 주세요.")
    @Length(max = 200)
    private final String task;

    /**
     * 생성자
     *
     * @param title : 수정하려는 일정의 제목
     * @param task  : 수정하려는 일정의 내용
     */
    public UpdatePlanRequestDto(
            String title
            , String task
    ) {
        this.title = title;
        this.task = task;
    }
}