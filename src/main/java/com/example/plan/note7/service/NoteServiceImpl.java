package com.example.plan.note7.service;

import com.example.plan.member7.repository.MemberRepository;
import com.example.plan.note7.dto.response.NoteResponseDto;
import com.example.plan.note7.entity.Note;
import com.example.plan.note7.repository.NoteRepository;
import com.example.plan.plan7.entity.Plan;
import com.example.plan.plan7.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 댓글 C 완료
 *
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
}
