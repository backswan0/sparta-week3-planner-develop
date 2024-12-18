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
 *
 *
 *
 */

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{
    private final PlanRepository planRepository;
    private final NoteRepository noteRepository;

    @Override
    public NoteResponseDto save(String content, Long planId) {
        Plan foundplan = planRepository.findByIdOrElseThrow(planId);

        Note noteToSave = new Note(content);

        noteToSave.setPlan(foundplan);
        noteToSave.setMember(foundplan.getMember());

        Note savedNote = noteRepository.save(noteToSave);

        return NoteResponseDto.toDto(savedNote);
    }

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
}