package com.example.plan.note7.controller;

import com.example.plan.note7.dto.request.CreateNoteRequestDto;
import com.example.plan.note7.dto.request.UpdateNoteRequestDto;
import com.example.plan.note7.dto.response.NoteResponseDto;
import com.example.plan.note7.service.NoteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    // 속성
    private final NoteServiceImpl noteService;

    /**
     * 기능
     * CREATE - 댓글 생성
     *
     * @param requestDto : CreateNoteRequestDto
     * @return NoteResponseDto, HttpStatus 201 CREATED
     */
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

    /**
     * 기능
     * READ - 댓글 목록 조회
     *
     * @return List<NoteResponseDto>, HttpStatus 200 OK
     */
    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> findAll() {
        List<NoteResponseDto> allNotes = new ArrayList<>();

        allNotes = noteService.findAll();

        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }

    /**
     * 기능
     * READ - 댓글 단건 조회
     *
     * @param id : 조회하려는 댓글의 식별자
     * @return NoteResponseDto, HttpStatus 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDto> findById(@PathVariable Long id) {
        NoteResponseDto responseDto = noteService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /*
    TODO 애플리케이션 실행은 잘 되었으나, 속도가 확실히 느려졌다.
     보통 11ms 내외였는데, 16ms - 20ms로 걸렸다.
     CRUD를 모두 생성한 다음 추가로 테스트해야겠다.
     */

    /**
     * 기능
     * 댓글 단건 수정
     *
     * @param id         : 수정하려는 댓글의 식별자
     * @param requestDto : UpdateNoteRequestDto
     * @return NoteResponseDto
     */
    @PatchMapping("/{id}")
    public ResponseEntity<NoteResponseDto> updateNote(
            @PathVariable Long id
            , @Valid @RequestBody UpdateNoteRequestDto requestDto
    ) {
        NoteResponseDto responseDto = noteService.updateNote(
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
        noteService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}