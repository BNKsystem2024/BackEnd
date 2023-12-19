package com.bnksys.onemind.apis.repositories;

import com.bnksys.onemind.apis.entities.Received_badge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedBadgeRepository extends JpaRepository<Received_badge, Long> {

    List<Received_badge> findByUserId(Integer userId);

}
