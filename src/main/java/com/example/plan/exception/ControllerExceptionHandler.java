package com.example.plan.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseMessage> handleValidationException(MethodArgumentNotValidException e) {

        HttpStatusCode statusCode = e.getStatusCode();

        List<String> errors = new ArrayList<>();

        errors = e.getFieldErrors()
                .stream()
                .map(error -> error.getField() + " 필드에서 오류가 발생했습니다. " + error.getDefaultMessage())
                .toList();


        int value = statusCode.value();

        String join = String.join(", ", errors);

        return new ResponseEntity<>(
                new ErrorResponseMessage(value, join), statusCode);
    }
}