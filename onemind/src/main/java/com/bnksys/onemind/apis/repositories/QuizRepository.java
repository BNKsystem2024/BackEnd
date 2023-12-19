package com.bnksys.onemind.apis.repositories;


import com.bnksys.onemind.apis.dtos.QuizResponse;
import com.bnksys.onemind.apis.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT new com.bnksys.onemind.apis.dtos.QuizResponse(quiz.id, quiz.question, quiz.answer, quiz.commentary, quiz.level) " +
        "FROM Quiz quiz " +
        "WHERE quiz.level = :level AND quiz.id NOT IN (SELECT sp.quiz.id FROM Solved_problem sp WHERE sp.isCorrect = true AND sp.user.id = :userId) " +
        "ORDER BY function('rand') LIMIT 1")
    QuizResponse findRandom_Quiz(@Param("level") int level, @Param("userId") int userId);


}
