package com.example.plan.exception;

public class AlreadyDeletedException extends RuntimeException {

  public AlreadyDeletedException() {
    super(ErrorMessage.DATA_ALREADY_DELETED);
  }
}
