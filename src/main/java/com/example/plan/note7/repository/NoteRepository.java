package com.example.plan.note7.repository;

import com.example.plan.note7.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * 기능
     * 댓글의 식별자로 댓글 단건 조회
     *
     * @param id : 조회하려는 댓글의 식별자
     * @return Note
     */
    default Note findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                        , "입력된 id가 존재하지 않습니다. 다시 입력해 주세요."
                )
        );
    }

    /**
     * 기능
     * 댓글 소프트 딜리트
     *
     * @param id : 삭제하려는 댓글의 식별자
     * @return int
     */
    @Transactional
    @Modifying
    @Query(
            "UPDATE Note n " +
                    "SET n.isDeleted = TRUE, n.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE n.id = :id " +
                    "AND n.isDeleted IS NULL"
    )
    int softDeleteById(Long id);

    /**
     * 기능
     * 일정이 삭제될 때 해당 일정에 작성된 댓글을 모두 소프트 딜리트하는 메서드
     *
     * @param id : 외래 키로 가진 일정의 식별자
     */
    @Transactional
    @Modifying
    @Query(
            "UPDATE Note n " +
                    "SET n.isDeleted = TRUE, n.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE n.plan.id = :id " +
                    "AND n.isDeleted IS NULL"
    )
    void softDeleteByPlanId(Long id);

    /**
     * 기능
     * 사용자가 삭제될 때 해당 사용자가 작성한 댓글을 모두 소프트 딜리트하는 메서드
     *
     * @param id : 외래 키로 가진 사용자의 식별자
     */
    @Transactional
    @Modifying
    @Query(
            "UPDATE Note n " +
                    "SET n.isDeleted = TRUE, n.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE n.member.id = :id " +
                    "AND n.isDeleted IS NULL"
    )
    void softDeleteByMemberId(Long id);
}