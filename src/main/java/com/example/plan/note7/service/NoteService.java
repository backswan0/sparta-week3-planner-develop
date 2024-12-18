package com.example.plan.note7.service;

import com.example.plan.note7.dto.response.NoteResponseDto;

import java.util.List;

/**
 * 댓글 C 완료
 * 댓글 R 완료 (전체 조회)
 */

public interface NoteService {

    /**
     * 기능
     * 일정 저장
     *
     * @param content : 댓글 내용
     * @param planId  : 해당 댓글이 작성된 일정의 식별자
     * @return NoteResponseDto
     */
    NoteResponseDto save(
            String content
            , Long planId
    );

    /**
     * 기능
     * 댓글 목록 찾기
     *
     * @return List<NoteResponseDto>
     */
    List<NoteResponseDto> findAll();

    /**
     * 기능
     * 댓글 단건을 id로 찾기
     *
     * @param id : 조회하려는 댓글의 식별자
     * @return NoteResponseDto
     */
    NoteResponseDto findById(Long id);

    /**
     * 기능
     * 댓글 단건 수정
     *
     * @param id      : 수정하려는 댓글의 식별자
     * @param content : 수정하려는 댓글의 내용
     * @return NoteResponseDto
     */
    NoteResponseDto updateNote(
            Long id
            , String content
    );
}
