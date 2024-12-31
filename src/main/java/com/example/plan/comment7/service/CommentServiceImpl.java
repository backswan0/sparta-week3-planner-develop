package com.example.plan.comment7.service;

import com.example.plan.comment7.dto.response.CommentResponseDto;
import com.example.plan.comment7.entity.Comments;
import com.example.plan.comment7.repository.CommentRepository;
import com.example.plan.plan7.entity.Plan;
import com.example.plan.plan7.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
        Plan foundPlan = planRepository
                .findByIdAndIsDeletedFalse(planId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                ); // todo

        Comments commentToSave = new Comments(content);

        commentToSave.updatePlan(foundPlan);

        commentToSave.updateMember(foundPlan.getMember());

//        commentToSave.setMember(foundplan.getMember());

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

        Comments foundComment = commentRepository
                .findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                ); // todo

        return CommentResponseDto.toDto(foundComment);
    }

    @Transactional
    @Override
    public CommentResponseDto updateComment(
            Long commentId
            , String content
    ) {
        Comments foundComment = commentRepository
                .findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                ); // todo

        foundComment.updateContent(content);

        Comments updatedComment = commentRepository.save(foundComment);

        return CommentResponseDto.toDto(updatedComment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        Comments foundComment = commentRepository
                .findByIdAndIsDeletedFalse(commentId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Id does not exist"
                        )
                ); // todo

        if (foundComment.getIsDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "The requested Data has already been deleted"
            );
        } // todo

        foundComment.markAsDeleted();
    }
}