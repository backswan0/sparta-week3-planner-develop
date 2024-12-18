package com.example.plan.note7.controller;

import com.example.plan.note7.dto.request.CreateNoteRequestDto;
import com.example.plan.note7.dto.response.NoteResponseDto;
import com.example.plan.note7.service.NoteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 댓글 C 완료
 *
 *
 *
 *
 */

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    // 속성
    private final NoteServiceImpl noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDto> save(
            @Valid @RequestBody CreateNoteRequestDto requestDto
    ) {
        NoteResponseDto savedNote = noteService.save
                (
                        requestDto.getContent()
                        , requestDto.getPlanId()
                );
        return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
    }
}