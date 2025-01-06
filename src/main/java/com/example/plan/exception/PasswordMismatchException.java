package com.example.plan.exception;

public class PasswordMismatchException extends RuntimeException {

  public PasswordMismatchException() {
    super(ErrorMessage.PASSWORD_NOT_MATCH);
  }
}
