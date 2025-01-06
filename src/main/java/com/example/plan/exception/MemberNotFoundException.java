package com.example.plan.exception;

public class MemberNotFoundException extends RuntimeException {

  public MemberNotFoundException() {
    super(ErrorMessage.MEMBER_NOT_FOUND);
  }
}