package com.example.plan.exception.mismatch;

import com.example.plan.exception.ErrorMessage;

public class PasswordMismatchException extends MismatchException {

  public PasswordMismatchException() {
    super(ErrorMessage.PASSWORD_NOT_MATCH);
  }
}