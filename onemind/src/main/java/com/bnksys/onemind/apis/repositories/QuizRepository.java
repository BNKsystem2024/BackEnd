package com.bnksys.onemind.apis.repositories;


import com.bnksys.onemind.apis.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
