package com.example.plan.comment7.service;

import com.example.plan.comment7.dto.response.CommentResponseDto;
import com.example.plan.comment7.entity.Comment;
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
    // 속성
    private final PlanRepository planRepository;
    private final CommentRepository commentRepository;

    /**
     * 기능
     * 댓글 저장
     *
     * @param content : 댓글 내용
     * @param planId  : 해당 댓글이 작성된 일정의 식별자
     * @return : CommentResponseDto
     */
    @Override
    public CommentResponseDto save(String content, Long planId) {
        Plan foundplan = planRepository.findByIdOrElseThrow(planId);

        Comment commentToSave = new Comment(content);

        commentToSave.setPlan(foundplan);
        commentToSave.setMember(foundplan.getMember());

        Comment savedComment = commentRepository.save(commentToSave);

        return CommentResponseDto.toDto(savedComment);
    }

    /**
     * 기능
     * 댓글 목록 조회
     *
     * @return List<CommentResponseDto>
     */
    @Transactional(readOnly = true)
    @Override
    public List<CommentResponseDto> findAll() {

        List<CommentResponseDto> allComments = new ArrayList<>();

        allComments = commentRepository.findAll()
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();

        return allComments;
    }

    /**
     * 기능
     * 댓글 단건을 id로 찾기
     *
     * @param id : 조회하려는 댓글의 식별자
     * @return CommentResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public CommentResponseDto findById(Long id) {

        Comment foundComment = commentRepository.findByIdOrElseThrow(id);

        return CommentResponseDto.toDto(foundComment);
    }

    /**
     * 기능
     * 댓글 단건 수정
     *
     * @param id      : 수정하려는 댓글의 식별자
     * @param content : 수정하려는 댓글의 내용
     * @return CommentResponseDto
     */
    @Override
    public CommentResponseDto updateComment(
            Long id
            , String content
    ) {
        Comment commentToUpdate = commentRepository.findByIdOrElseThrow(id);

        commentToUpdate.update(content);

        Comment updatedComment = commentRepository.save(commentToUpdate);

        return CommentResponseDto.toDto(updatedComment);
    }

    /**
     * 기능
     * 댓글 단건 삭제
     *
     * @param id : 삭제하려는 댓글의 식별자
     */
    @Override
    public void delete(Long id) {
        int rowsAffected = commentRepository.softDeleteById(id);

        if (rowsAffected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
                    , "이미 삭제되었거나 존재하지 않는 id입니다."
            );
        }
    }
}