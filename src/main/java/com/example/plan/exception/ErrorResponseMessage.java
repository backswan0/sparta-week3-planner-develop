package com.example.plan.exception;

import lombok.Getter;

/**
 * soft delete - member 완료
 * 중복되는 이메일은 가입할 수 없도록 리팩토링 완료 (unique = true 추가하여)
 *
 */

@Getter
public class ErrorResponseMessage {
    // 속성
    private final int status;
    private final String message;

    // 생성자
    public ErrorResponseMessage(
            int status
            , String message
    ) {
        this.status = status;
        this.message = message;
    }
}