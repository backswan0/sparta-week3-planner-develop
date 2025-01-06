package com.example.plan.exception;

public class PlanNotFoundException extends RuntimeException {

  public PlanNotFoundException() {
    super(ErrorMessage.PLAN_NOT_FOUND);
  }
}
