package com.example.plan.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyDeletedException extends RuntimeException {

  private final String errorCode;
  private final HttpStatus httpStatus;

  public AlreadyDeletedException() {
    super(ErrorMessage.DATA_ALREADY_DELETED);

    this.errorCode = "ERROR_DATA_ALREADY_DELETED";
    this.httpStatus = HttpStatus.CONFLICT;
  }
}
