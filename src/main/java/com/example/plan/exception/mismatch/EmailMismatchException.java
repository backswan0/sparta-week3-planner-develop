package com.example.plan.exception.mismatch;

import com.example.plan.exception.ErrorMessage;
import lombok.Getter;

@Getter
public class EmailMismatchException extends MismatchException {

  public EmailMismatchException(
  ) {
    super(ErrorMessage.EMAIL_NOT_MATCH);
  }
}