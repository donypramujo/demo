package com.jaxi.rest.controller;

import com.jaxi.entity.TeamLeader;
import com.jaxi.repository.TeamLeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamLeaderController {


    @Autowired
    private TeamLeaderRepository teamLeaderRepository;

    @GetMapping("teamleader/all")
    public ResponseEntity<List<TeamLeader>> all(){
        return ResponseEntity.ok().body(teamLeaderRepository.findAll());
    }

}
