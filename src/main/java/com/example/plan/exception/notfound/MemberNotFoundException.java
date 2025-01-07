package com.example.plan.exception.notfound;

import com.example.plan.exception.ErrorMessage;

public class MemberNotFoundException extends NotFoundException {

  public MemberNotFoundException() {
    super(ErrorMessage.MEMBER_NOT_FOUND);
  }
}