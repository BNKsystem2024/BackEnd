package com.bnksys.onemind.apis.repositories;


import com.bnksys.onemind.apis.entities.Solved_problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedProblemRepository extends JpaRepository<Solved_problem, Long> {

    Page<Solved_problem> findByUserId(Integer id, Pageable pageable);

}
