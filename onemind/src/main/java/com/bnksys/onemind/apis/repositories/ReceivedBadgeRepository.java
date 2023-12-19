package com.bnksys.onemind.apis.repositories;

import com.bnksys.onemind.apis.entities.Received_badge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.transaction.Transactional;

public interface ReceivedBadgeRepository extends JpaRepository<Received_badge, Long> {

    List<Received_badge> findByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Received_badge (user_id, badge_id) VALUES (1, :badgeId)", nativeQuery = true)
    void saveReceived_Badge(@Param("badgeId") int badgeId);

}
