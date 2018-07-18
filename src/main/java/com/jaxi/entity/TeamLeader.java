package com.jaxi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("tl")
public class TeamLeader extends User {

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Coordinator coordinator;

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
