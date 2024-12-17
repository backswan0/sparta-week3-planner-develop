package com.example.plan.exception;

import lombok.Getter;

// 6단계까지 완료

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