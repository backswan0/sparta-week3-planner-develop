package com.example.plan.exception;

public class CommentNotFoundException extends RuntimeException {

  public CommentNotFoundException() {
    super(ErrorMessage.COMMENT_NOT_FOUND);
  }
}
