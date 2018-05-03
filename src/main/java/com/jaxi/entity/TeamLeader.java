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
    private AreaSalesManager areaSalesManager;

    public AreaSalesManager getAreaSalesManager() {
        return areaSalesManager;
    }

    public void setAreaSalesManager(AreaSalesManager areaSalesManager) {
        this.areaSalesManager = areaSalesManager;
    }
}
