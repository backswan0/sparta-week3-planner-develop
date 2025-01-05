package com.example.plan.comment7.service;

import com.example.plan.comment7.dto.response.CommentResponseDto;
import java.util.List;

public interface CommentService {

  CommentResponseDto createComment(
      String content,
      Long planId
  );

  List<CommentResponseDto> readAllComments();

  CommentResponseDto readCommentById(Long commentId);

  CommentResponseDto updateComment(
      Long commentId,
      String content
  );

  void deleteComment(Long commentId);
}