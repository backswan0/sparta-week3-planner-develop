package com.example.plan.exception;

public class PlanNotFoundException extends RuntimeException {

  public PlanNotFoundException(String message) {
    super(message);
  }
}
