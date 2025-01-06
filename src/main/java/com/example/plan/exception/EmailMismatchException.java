package com.example.plan.exception;

public class EmailMismatchException extends RuntimeException {

  public EmailMismatchException() {
    super(ErrorMessage.EMAIL_NOT_MATCH);
  }
}
