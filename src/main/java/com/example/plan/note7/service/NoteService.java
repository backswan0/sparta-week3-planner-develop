package com.example.plan.note7.service;

import com.example.plan.note7.dto.response.NoteResponseDto;

import java.util.List;

/**
 * 댓글 C 완료
 * 댓글 R 완료 (전체 조회)
 *
 *
 *
 */

public interface NoteService {

    NoteResponseDto save(
            String content
            , Long planId
    );

    List<NoteResponseDto> findAll();
}
