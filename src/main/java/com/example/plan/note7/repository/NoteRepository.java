package com.example.plan.note7.repository;

import com.example.plan.note7.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

public interface NoteRepository extends JpaRepository<Note, Long> {

    default Note findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                        , "입력된 id가 존재하지 않습니다. 다시 입력해 주세요."
                )
        );
    }

    @Transactional
    @Modifying
    @Query(
            "UPDATE Note n " +
                    "SET n.isDeleted = TRUE, n.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE n.id = :id " +
                    "AND n.isDeleted IS NULL"
    )
    int softDeleteById(Long id);

    @Transactional
    @Modifying
    @Query(
            "UPDATE Note n " +
                    "SET n.isDeleted = TRUE, n.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE n.plan.id = :id " +
                    "AND n.isDeleted IS NULL"
    )
    void softDeleteByPlanId(Long id);


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