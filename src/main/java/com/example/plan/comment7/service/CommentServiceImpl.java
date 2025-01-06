package com.example.plan.comment7.service;

import com.example.plan.comment7.dto.response.CommentResponseDto;
import com.example.plan.comment7.entity.Comments;
import com.example.plan.comment7.repository.CommentRepository;
import com.example.plan.exception.AlreadyDeletedException;
import com.example.plan.exception.CommentNotFoundException;
import com.example.plan.exception.PlanNotFoundException;
import com.example.plan.plan7.entity.Plan;
import com.example.plan.plan7.repository.PlanRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

  private final PlanRepository planRepository;
  private final CommentRepository commentRepository;

  @Transactional
  @Override
  public CommentResponseDto createComment(
      String content,
      Long planId
  ) {
    Plan foundPlan = findPlanById(planId);

    Comments commentToSave = new Comments(content);

    commentToSave.updatePlan(foundPlan);

    commentToSave.updateMember(foundPlan.getMember());

    Comments savedComment = commentRepository.save(commentToSave);

    return CommentResponseDto.toDto(savedComment);
  }

  @Transactional(readOnly = true)
  @Override
  public List<CommentResponseDto> readAllComments() {

    List<CommentResponseDto> allComments = new ArrayList<>();

    allComments = commentRepository
        .findAllByIsDeletedFalse()
        .stream()
        .map(CommentResponseDto::toDto)
        .toList();

    return allComments;
  }

  @Transactional(readOnly = true)
  @Override
  public CommentResponseDto readCommentById(Long commentId) {

    Comments foundComment = findCommentById(commentId);

    return CommentResponseDto.toDto(foundComment);
  }

  @Transactional
  @Override
  public CommentResponseDto updateComment(
      Long commentId
      , String content
  ) {
    Comments foundComment = findCommentById(commentId);

    foundComment.updateContent(content);

    Comments updatedComment = commentRepository.save(foundComment);

    return CommentResponseDto.toDto(updatedComment);
  }

  @Transactional
  @Override
  public void deleteComment(Long commentId) {
    Comments foundComment = findCommentById(commentId);

    if (foundComment.getIsDeleted()) {
      throw new AlreadyDeletedException();
    }

    foundComment.markAsDeleted();
  }

  private Plan findPlanById(Long planId) {
    return planRepository.findByIdAndIsDeletedFalse(planId)
        .orElseThrow(
            PlanNotFoundException::new
        );
  }

  private Comments findCommentById(Long commentId) {
    return commentRepository.findByIdAndIsDeletedFalse(commentId)
        .orElseThrow(
            CommentNotFoundException::new
        );
  }
}