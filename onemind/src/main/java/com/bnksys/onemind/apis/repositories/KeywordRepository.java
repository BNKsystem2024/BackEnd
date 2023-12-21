package com.bnksys.onemind.apis.repositories;

import com.bnksys.onemind.apis.entities.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    boolean existsByWord(String word);

}
