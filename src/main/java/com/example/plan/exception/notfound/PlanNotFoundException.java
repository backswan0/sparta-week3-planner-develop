package com.example.plan.exception.notfound;

import com.example.plan.exception.ErrorMessage;

public class PlanNotFoundException extends NotFoundException {

  public PlanNotFoundException() {
    super(ErrorMessage.PLAN_NOT_FOUND);
  }
}