package com.example.plan.exception;

public class MemberNotFoundException extends RuntimeException {

  public MemberNotFoundException(String message) {
    super(message);
  }
}