package com.example.plan.note7.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateNoteRequestDto {
    // 속성
    @NotBlank(message = "댓글 내용 입력은 필수입니다.")
    @Length(max = 200)
    private final String content;

    public UpdateNoteRequestDto(String content) {
        this.content = content;
    }
}
