package com.example.plan.note7.dto.response;

import com.example.plan.note7.entity.Note;
import com.example.plan.plan7.dto.response.PlanResponseDto;
import com.example.plan.plan7.entity.Plan;
import lombok.Getter;

@Getter
public class NoteResponseDto {
    // 속성
    private final Long id;
    private final String content;
    private final PlanResponseDto plan;

    /**
     * @param id      : 댓글 식별자
     * @param content : 댓글 내용
     * @param plan    : 댓글이 작성되는 일정
     */
    public NoteResponseDto(
            Long id
            , String content
            , Plan plan
    ) {
        this.id = id;
        this.content = content;
        this.plan = PlanResponseDto.toDto(plan);
    }

    /**
     * 기능
     * Note 객체를 response DTO 타입으로 변환
     *
     * @param note: Note
     * @return NoteResponseDto
     */
    public static NoteResponseDto toDto(Note note) {
        return new NoteResponseDto(
                note.getId()
                , note.getContent()
                , note.getPlan()
        );
    }
}