package com.example.plan.exception;

import com.example.plan.exception.mismatch.MismatchException;
import com.example.plan.exception.notfound.NotFoundException;
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
        "ERROR_INVALID_INPUT",
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleOtherException() {
    return handleException(
        new Exception(ErrorMessage.INVALID_PATH),
        "ERROR_INVALID_PATH",
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(MismatchException.class)
  public ResponseEntity<Map<String, Object>> handleUnAuthorizedException(
      MismatchException ex
  ) {
    return handleException(
        ex,
        ex.getErrorCode(),
        ex.getHttpStatus()
    );
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFoundException(
      NotFoundException ex
  ) {
    return handleException(
        ex,
        ex.getErrorCode(),
        ex.getHttpStatus()
    );
  }

  @ExceptionHandler(AlreadyDeletedException.class)
  public ResponseEntity<Map<String, Object>> handleAlreadyDeletedException(
      AlreadyDeletedException ex
  ) {
    return handleException(
        ex,
        ex.getErrorCode(),
        ex.getHttpStatus()
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