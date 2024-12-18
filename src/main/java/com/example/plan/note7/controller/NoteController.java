package com.example.plan.note7.controller;

import com.example.plan.note7.dto.request.CreateNoteRequestDto;
import com.example.plan.note7.dto.response.NoteResponseDto;
import com.example.plan.note7.service.NoteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 댓글 C 완료
 * 댓글 R 완료 (전체 조회)
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

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> findAll() {
        List<NoteResponseDto> allNotes = new ArrayList<>();

        allNotes = noteService.findAll();

        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }
}