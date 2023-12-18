package com.bnksys.onemind.apis.repositories;

import com.bnksys.onemind.apis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);
}
