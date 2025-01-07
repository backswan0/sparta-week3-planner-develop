package com.example.plan.exception.notfound;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

  private final String errorCode;
  private final HttpStatus httpStatus;

  public NotFoundException(String message) {
    super(message);
    this.errorCode = "ERROR_NOT_FOUND";
    this.httpStatus = HttpStatus.NOT_FOUND;
  }
}