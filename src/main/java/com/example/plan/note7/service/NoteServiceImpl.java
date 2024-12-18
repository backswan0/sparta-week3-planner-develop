package com.example.plan.note7.service;

import com.example.plan.note7.dto.response.NoteResponseDto;
import com.example.plan.note7.entity.Note;
import com.example.plan.note7.repository.NoteRepository;
import com.example.plan.plan7.entity.Plan;
import com.example.plan.plan7.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 댓글 C 완료
 * 댓글 R 완료 (전체 조회)
 */

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {
    // 속성
    private final PlanRepository planRepository;
    private final NoteRepository noteRepository;

    /**
     * 기능
     * 댓글 저장
     *
     * @param content : 댓글 내용
     * @param planId  : 해당 댓글이 작성된 일정의 식별자
     * @return : NoteResponseDto
     */
    @Override
    public NoteResponseDto save(String content, Long planId) {
        Plan foundplan = planRepository.findByIdOrElseThrow(planId);

        Note noteToSave = new Note(content);

        noteToSave.setPlan(foundplan);
        noteToSave.setMember(foundplan.getMember());

        Note savedNote = noteRepository.save(noteToSave);

        return NoteResponseDto.toDto(savedNote);
    }

    /**
     * 기능
     * 댓글 목록 조회
     *
     * @return List<NoteResponseDto>
     */
    @Transactional(readOnly = true)
    @Override
    public List<NoteResponseDto> findAll() {

        List<NoteResponseDto> allNotes = new ArrayList<>();

        allNotes = noteRepository.findAll()
                .stream()
                .map(NoteResponseDto::toDto)
                .toList();

        return allNotes;
    }

    /**
     * 기능
     * 댓글 단건을 id로 찾기
     *
     * @param id : 조회하려는 댓글의 식별자
     * @return NoteResponseDto
     */
    @Transactional(readOnly = true)
    @Override
    public NoteResponseDto findById(Long id) {

        Note foundNote = noteRepository.findByIdOrElseThrow(id);

        return NoteResponseDto.toDto(foundNote);
    }

    /**
     * 기능
     * 댓글 단건 수정
     *
     * @param id      : 수정하려는 댓글의 식별자
     * @param content : 수정하려는 댓글의 내용
     * @return NoteResponseDto
     */
    @Override
    public NoteResponseDto updateNote(
            Long id
            , String content
    ) {
        Note noteToUpdate = noteRepository.findByIdOrElseThrow(id);

        noteToUpdate.update(content);

        Note updatedNote = noteRepository.save(noteToUpdate);

        return NoteResponseDto.toDto(updatedNote);
    }
}