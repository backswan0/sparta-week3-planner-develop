package com.example.plan.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class LayerExceptionHandler {
    /**
     * 기능
     * 입력 값 검증 실패 시 예외 처리
     *
     * @param e : MethodArgumentNotValidException
     * @return ErrorResponseMessage
     */
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

    /**
     * 기능
     * HTTP 요청에서 발생한 예외 처리
     *
     * @param e : ResponseStatusException
     * @return ErrorResponseMessage
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseMessage> handleResponseStatusException(ResponseStatusException e) {

        HttpStatusCode statusCode = e.getStatusCode();

        return new ResponseEntity<>(
                new ErrorResponseMessage(statusCode.value(), e.getReason()), statusCode
        );
    }

    /**
     * 기능
     * MethodArgumentNotValidException, ResponseStatusException 외에 발생한 예외 처리
     *
     * @param e : Exception
     * @return ErrorResponseMessage
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseMessage> handleException(Exception e) {

        HttpStatusCode statusCode = HttpStatusCode.valueOf(500);

        return new ResponseEntity<>(
                new ErrorResponseMessage(statusCode.value(), "오류가 발생했습니다."), statusCode
        );
    }
}