package com.example.plan.comment7.controller;

import com.example.plan.comment7.dto.request.CreateCommentRequestDto;
import com.example.plan.comment7.dto.request.UpdateCommentRequestDto;
import com.example.plan.comment7.dto.response.CommentResponseDto;
import com.example.plan.comment7.service.CommentServiceImpl;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentServiceImpl commentService;

  @PostMapping
  public ResponseEntity<CommentResponseDto> createComment(
      @Valid @RequestBody CreateCommentRequestDto requestDto
  ) {
    CommentResponseDto savedComment = commentService
        .createComment
            (
                requestDto.content(),
                requestDto.planId()
            );

    return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<CommentResponseDto>> readAllComments() {
    List<CommentResponseDto> commentList = new ArrayList<>();

    commentList = commentService.readAllComments();

    return new ResponseEntity<>(commentList, HttpStatus.OK);
  }

  @GetMapping("/{commentId}")
  public ResponseEntity<CommentResponseDto> readCommentById(
      @PathVariable Long commentId
  ) {
    CommentResponseDto responseDto = commentService
        .readCommentById(commentId);

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @PatchMapping("/{commentId}")
  public ResponseEntity<CommentResponseDto> updateComment(
      @PathVariable Long commentId,
      @Valid @RequestBody UpdateCommentRequestDto requestDto
  ) {
    CommentResponseDto responseDto = commentService
        .updateComment(
            commentId,
            requestDto.content()
        );

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> delete(
      @PathVariable Long commentId
  ) {
    commentService.deleteComment(commentId);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}