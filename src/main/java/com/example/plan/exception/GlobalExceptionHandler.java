package com.example.plan.exception;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationException(
      MethodArgumentNotValidException e
  ) {

    List<String> errors = new ArrayList<>();

    errors = e.getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage
        )
        .toList();

    return handleException(
        new Exception(String.join(". ", errors)),
        ErrorMessage.ERROR_INVALID_INPUT,
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleOtherException() {
    return handleException(
        new Exception(ErrorMessage.INVALID_PATH),
        ErrorMessage.ERROR_INVALID_PATH,
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(EmailMismatchException.class)
  public ResponseEntity<Map<String, Object>> handleEmailMismatchException(
      EmailMismatchException ex
  ) {
    return handleException(
        ex,
        ErrorMessage.ERROR_EMAIL_NOT_MATCH,
        HttpStatus.UNAUTHORIZED
    );
  }

  @ExceptionHandler(PasswordMismatchException.class)
  public ResponseEntity<Map<String, Object>> handlePasswordMismatchException(
      PasswordMismatchException ex
  ) {
    return handleException(
        ex,
        ErrorMessage.ERROR_PASSWORD_NOT_MATCH,
        HttpStatus.UNAUTHORIZED
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
        ErrorMessage.ERROR_COMMENT_NOT_FOUND,
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(AlreadyDeletedException.class)
  public ResponseEntity<Map<String, Object>> handleAlreadyDeletedException(
      AlreadyDeletedException ex
  ) {
    return handleException(
        ex,
        ErrorMessage.ERROR_DATA_ALREADY_DELETED,
        HttpStatus.CONFLICT
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