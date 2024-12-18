package com.example.plan.note7.service;

import com.example.plan.note7.dto.response.NoteResponseDto;

/**
 * 댓글 C 완료
 *
 *
 *
 *
 */

public interface NoteService {

    NoteResponseDto save(
            String content
            , Long planId
    );
}
