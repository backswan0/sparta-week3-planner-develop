package com.example.plan.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

// 6단계까지 완료

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseMessage> handleValidationException(MethodArgumentNotValidException e) {
        HttpStatusCode statusCode = e.getStatusCode();

        List<String> errors = new ArrayList<>();

        errors = e.getFieldErrors()
                .stream()
                .map(error ->
                        error.getField()
                                + " 필드에서 오류가 발생했습니다. "
                                + error.getDefaultMessage()
                )
                .toList();

        return new ResponseEntity<>(
                new ErrorResponseMessage(statusCode.value(), String.join(", ", errors)), statusCode
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseMessage> handleResponseStatusException(ResponseStatusException e) {

        HttpStatusCode statusCode = e.getStatusCode();

        return new ResponseEntity<>(
                new ErrorResponseMessage(statusCode.value(), e.getReason()), statusCode
        );
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponseMessage> handleRuntimeException(RuntimeException e) {
//
//        HttpStatusCode statusCode = HttpStatusCode.valueOf(401);
//
//        return new ResponseEntity<>(
//          new ErrorResponseMessage(statusCode.value(), "로그인을 해주세요"), statusCode
//        );
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseMessage> handleException(Exception e) {

        HttpStatusCode statusCode = HttpStatusCode.valueOf(500);

        return new ResponseEntity<>(
                new ErrorResponseMessage(statusCode.value(), "오류가 발생했습니다."), statusCode
        );
    }
}