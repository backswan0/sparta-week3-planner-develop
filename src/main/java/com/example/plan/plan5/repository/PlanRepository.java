package com.example.plan.plan5.repository;

import com.example.plan.plan5.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    /**
     * 소프트 딜리트가 된 일정을 제외한 목록 조회
     *
     * @return List<Plan>
     */
    @Query("SELECT p From Plan p WHERE p.isDeleted IS NULL")
    List<Plan> findAllExceptDeleted();

    /**
     * 기능
     * 소프트 딜리트가 된 일정을 제외한 단건 조회용 메서드
     *
     * @param id : 조회하려는 일정의 식별자
     * @return Optional<Plan>
     */
    @Query("SELECT p From Plan p WHERE p.id = :id AND p.isDeleted IS NULL")
    Optional<Plan> findByIdExceptDeleted(Long id);

    /**
     * 기능
     * 일정의 식별자로 일정 단건 조회
     *
     * @param id : 일정의 식별자
     * @return Plan
     */
    default Plan findByIdOrElseThrow(Long id) {
        return findByIdExceptDeleted(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                        , "입력된 id가 존재하지 않습니다. 다시 입력해 주세요."
                )
        );
    }

    /**
     * 기능
     * 일정 단건 소프트 딜리트
     *
     * @param id : 소프트 딜리트를 진행하려는 일정의 식별자
     * @return int
     */
    @Transactional
    @Modifying
    @Query(
            "UPDATE Plan p " +
                    "SET p.isDeleted = true, p.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE p.id = :id " +
                    "AND p.isDeleted IS NULL"
    )
    int softDeleteById(Long id);

    /**
     * 기능
     * 외래 키를 활용하여 사용자가 삭제될 때, 해당 사용자가 작성한 일정도 같이 소프트 딜리트 진행
     *
     * @param id : 일정을 작성한 사용자의 식별자
     */
    @Transactional
    @Modifying
    @Query(
            "UPDATE Plan p " +
                    "SET p.isDeleted = TRUE, p.deletedAt = CURRENT_TIMESTAMP " +
                    "WHERE p.member.id = :id " +
                    "AND p.isDeleted IS NULL"
    )
    /*
    "AND p.member.isDeleted = TRUE " + member의 id만 알 뿐 member의 isDeleted를 모르므로
    만약 알려면 join을 써야 하는데 mysql에 강한 의존성을 가진다. update에 join을 허용하는 건 일부 데이터베이스에서만 지원하기 때문에
    메서드는 행위만 정의(예를 들어서 ~을 통해서 삭제하는지만) 언제 하는지는 서비스에서
     */
    void softDeleteByMemberId(Long id);
}