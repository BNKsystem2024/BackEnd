package com.bnksys.onemind.apis.repositories;


import com.bnksys.onemind.apis.entities.Solved_problem;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolvedProblemRepository extends JpaRepository<Solved_problem, Long> {

    Page<Solved_problem> findByUserId(Integer userId, Pageable page);

    Integer countByUserId(Integer userId);

    List<Solved_problem> findByUserIdAndQuizId(Integer userId, Long quizId);

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

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Solved_problem (user_id, quiz_id, solved_date, is_correct) VALUES (:userId, :quizId, :solvedDate, :isCorrect)", nativeQuery = true)
    void saveSolvedQuiz(@Param("userId") int userId,
        @Param("quizId") Long quizId,
        @Param("solvedDate") String solvedDate,
        @Param("isCorrect") int isCorrect);

    @Query("SELECT COUNT(*) FROM Solved_problem sp WHERE sp.user.id = :userId AND sp.isCorrect = true")
    int countCorrectAnswers(@Param("userId") int userId);

    List<Solved_problem> findByUserId(Integer userId);

}
