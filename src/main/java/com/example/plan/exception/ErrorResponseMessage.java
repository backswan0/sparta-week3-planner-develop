package com.example.plan.exception;

import lombok.Getter;

@Getter
public class ErrorResponseMessage {
    // 속성
    private final int status;
    private final String message;

    /**
     * 생성자
     *
     * @param status  : HTTP 상태 코드
     * @param message : 오류 메시지
     */
    public ErrorResponseMessage(
            int status
            , String message
    ) {
        this.status = status;
        this.message = message;
    }
}