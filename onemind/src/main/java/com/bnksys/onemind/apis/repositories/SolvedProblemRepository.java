package com.bnksys.onemind.apis.repositories;


import com.bnksys.onemind.apis.entities.Solved_problem;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SolvedProblemRepository extends JpaRepository<Solved_problem, Long> {

    Page<Solved_problem> findByUserId(Integer userId, Pageable page);

    @Query(value = "SELECT user_id FROM (SELECT user_id, COUNT(*) as cnt FROM Solved_problem " +
        "WHERE is_correct = TRUE " +
        "GROUP BY user_id " +
        "ORDER BY cnt DESC " +
        "LIMIT 1) AS subquery", nativeQuery = true)
    Optional<Integer> findTopCorrectAnswerer();

    @Query(value = "SELECT user_id FROM (SELECT user_id, COUNT(*) as cnt FROM Solved_problem " +
        "WHERE is_correct = FALSE " +
        "GROUP BY user_id " +
        "ORDER BY cnt DESC " +
        "LIMIT 1) AS subquery", nativeQuery = true)
    Optional<Integer> findTopIncorrectAnswerer();

}
