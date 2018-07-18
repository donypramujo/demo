package com.jaxi.repository;

import com.jaxi.entity.Coordinator;
import com.jaxi.entity.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamLeaderRepository  extends JpaRepository<TeamLeader, Long> {


    public List<TeamLeader> findAllByCoordinator(Coordinator coordinator);

}
