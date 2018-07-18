package com.jaxi.repository;

import com.jaxi.entity.Canvasser;
import com.jaxi.entity.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CanvasserRepository extends JpaRepository<Canvasser,Long> {

    public List<Canvasser> findAllByTeamLeader(TeamLeader teamLeader);
}
