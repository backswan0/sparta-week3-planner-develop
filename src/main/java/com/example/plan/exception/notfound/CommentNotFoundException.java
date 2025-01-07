package com.example.plan.exception.notfound;

import com.example.plan.exception.ErrorMessage;

public class CommentNotFoundException extends NotFoundException {

  public CommentNotFoundException() {
    super(ErrorMessage.COMMENT_NOT_FOUND);
  }
}
