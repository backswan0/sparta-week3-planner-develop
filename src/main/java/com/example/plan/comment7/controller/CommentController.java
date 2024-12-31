package com.example.plan.comment7.controller;

import com.example.plan.comment7.dto.request.CreateCommentRequestDto;
import com.example.plan.comment7.dto.request.UpdateCommentRequestDto;
import com.example.plan.comment7.dto.response.CommentResponseDto;
import com.example.plan.comment7.service.CommentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    // 속성
    private final CommentServiceImpl commentService;

    /**
     * 기능
     * CREATE - 댓글 생성
     *
     * @param requestDto : CreateCommentRequestDto
     * @return CommentResponseDto, HttpStatus 201 CREATED
     */
    @PostMapping
    public ResponseEntity<CommentResponseDto> save(
            @Valid @RequestBody CreateCommentRequestDto requestDto
    ) {
        CommentResponseDto savedComment = commentService.save
                (
                        requestDto.getContent()
                        , requestDto.getPlanId()
                );
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    /**
     * 기능
     * READ - 댓글 목록 조회
     *
     * @return List<CommentResponseDto>, HttpStatus 200 OK
     */
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        List<CommentResponseDto> allComments = new ArrayList<>();

        allComments = commentService.findAll();

        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    /**
     * 기능
     * READ - 댓글 단건 조회
     *
     * @param id : 조회하려는 댓글의 식별자
     * @return CommentResponseDto, HttpStatus 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id) {
        CommentResponseDto responseDto = commentService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 기능
     * 댓글 단건 수정
     *
     * @param id         : 수정하려는 댓글의 식별자
     * @param requestDto : UpdateCommentRequestDto
     * @return CommentResponseDto
     */
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id
            , @Valid @RequestBody UpdateCommentRequestDto requestDto
    ) {
        CommentResponseDto responseDto = commentService.updateComment(
                id
                , requestDto.getContent()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    // TODO 나중에 댓글 제목이 추가될 수 있다고 가정해서 PatchMapping 사용

    /**
     * 기능
     * 댓글 단건 삭제
     * @param id : 삭제하려는 댓글의 식별자
     * @return HttpStatus 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}