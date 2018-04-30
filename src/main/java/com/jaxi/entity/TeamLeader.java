package com.jaxi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tl")
public class TeamLeader extends User {
}
