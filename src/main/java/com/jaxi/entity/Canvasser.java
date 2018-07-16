package com.jaxi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("cv")
public class Canvasser extends User {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TeamLeader teamLeader;

    public TeamLeader getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(TeamLeader teamLeader) {
        this.teamLeader = teamLeader;
    }


}
