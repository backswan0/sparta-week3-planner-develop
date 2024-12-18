package com.example.plan.plan5.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Getter
public class CreatePlanRequestDto {
    // 속성
    @NotBlank(message = "일정의 제목 입력은 필수입니다.")
    @Length (min = 1, max = 20)
    private final String title;

    @NotEmpty(message = "null과 빈값을 허용하지 않습니다. 공백으로 입력해 주세요.")
    @Length(max = 200)
    private final String task;

    @NotNull(message = "사용자 id 입력은 필수입니다.")
    private final Long userId;

    /**
     * 생성자
     *
     * @param title  : 생성하려는 일정의 제목
     * @param task   : 생성하려는 일정의 내용
     * @param userId : 해당 일정을 작성한 사용자의 식별자
     */
    public CreatePlanRequestDto(
            String title
            , String task
            , Long userId

    ) {
        this.title = title;
        this.task = task;
        this.userId = userId;
    }
}