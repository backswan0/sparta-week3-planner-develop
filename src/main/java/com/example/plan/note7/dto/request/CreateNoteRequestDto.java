package com.example.plan.note7.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateNoteRequestDto {

    @NotBlank(message = "댓글 내용 입력은 필수입니다.")
    @Length(max = 200)
    private final String content;

    @NotNull(message = "댓글을 생성할 일정의 id 입력은 필수입니다.")
    private final Long planId;

    /**
     * 생성자
     * @param content : 댓글 내용
     * @param planId : 댓글이 작성되는 일정의 식별자
     */
    public CreateNoteRequestDto(
            String content
            , Long planId
    ) {
        this.content = content;
        this.planId = planId;
    }
}