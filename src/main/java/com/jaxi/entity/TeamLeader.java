package com.jaxi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("tl")
public class TeamLeader extends User {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Coordinator coordinator;

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
