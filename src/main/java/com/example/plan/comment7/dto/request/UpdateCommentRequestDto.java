package com.example.plan.comment7.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateCommentRequestDto {
    // 속성
    @NotBlank(message = "댓글 내용 입력은 필수입니다.")
    @Length(max = 200)
    private final String content;

    /**
     * 생성자
     * @param content : 수정하려는 댓글의 내용
     */
    public UpdateCommentRequestDto(String content) {
        this.content = content;
    }
}