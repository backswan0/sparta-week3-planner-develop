package com.example.plan.exception.mismatch;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MismatchException extends RuntimeException {

  private final String errorCode;
  private final HttpStatus httpStatus;

  public MismatchException(String message) {
    super(message);
    this.errorCode = "ERROR_MISMATCH";
    this.httpStatus = HttpStatus.UNAUTHORIZED;
  }
}
