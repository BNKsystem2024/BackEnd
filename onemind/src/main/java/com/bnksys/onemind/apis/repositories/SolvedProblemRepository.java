package com.bnksys.onemind.apis.repositories;


import com.bnksys.onemind.apis.entities.Solved_problem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedProblemRepository extends JpaRepository<Solved_problem, Long> {

    List<Solved_problem> findByUserId(Integer userId);

}
