package com.example.plan.note7.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

/**
 * 댓글 C 완료
 *
 *
 *
 *
 */

@Getter
public class CreateNoteRequestDto {

    @NotEmpty(message = "null과 빈값을 허용하지 않습니다. 공백으로 입력해 주세요.")
    @Length(max = 200)
    private final String content;

    @NotNull(message = "일정 id 입력은 필수입니다.")
    private final Long planId;

    public CreateNoteRequestDto(
            String content
            , Long planId
    ) {
        this.content = content;
        this.planId = planId;
    }
}