package com.example.plan.exception;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseMessage> handleValidationException(
      MethodArgumentNotValidException e
  ) {
    HttpStatusCode statusCode = e.getStatusCode();

    List<String> errors = new ArrayList<>();

    errors = e.getFieldErrors()
        .stream()
        .map(error ->
            error.getField()
                + error.getDefaultMessage()
        )
        .toList();

    return new ResponseEntity<>(
        new ErrorResponseMessage(statusCode.value(), String.join(", ", errors)), statusCode
    );
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ErrorResponseMessage> handleResponseStatusException(
      ResponseStatusException e
  ) {
    HttpStatusCode statusCode = e.getStatusCode();

    return new ResponseEntity<>(
        new ErrorResponseMessage(statusCode.value(), e.getReason()), statusCode
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseMessage> handleException(Exception e) {

    HttpStatusCode statusCode = HttpStatusCode.valueOf(500);

    return new ResponseEntity<>(
        new ErrorResponseMessage(statusCode.value(), "오류가 발생했습니다."), statusCode
    );
  }

  @ExceptionHandler(MemberNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleMemberNotFoundException(
      MemberNotFoundException ex
  ) {
    return handleException(
        ex,
        ErrorMessage.ERROR_MEMBER_NOT_FOUND,
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(PlanNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handlePlanNotFoundException(
      PlanNotFoundException ex
  ) {
    return handleException(
        ex,
        ErrorMessage.ERROR_PLAN_NOT_FOUND,
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(CommentNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleCommentNotFoundException(
      CommentNotFoundException ex
  ) {
    return handleException(
        ex,
        ErrorMessage.COMMENT_NOT_FOUND,
        HttpStatus.NOT_FOUND
    );
  }

  private ResponseEntity<Map<String, Object>> handleException(
      Exception ex,
      String errorCode,
      HttpStatus status
  ) {
    String errorMessage = ex.getMessage();

    Map<String, Object> errorResponse = new LinkedHashMap<>();
    errorResponse.put("errorCode", errorCode);
    errorResponse.put("errorMessage", errorMessage);

    return new ResponseEntity<>(errorResponse, status);
  }
}