package com.jaxi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cd")
public class Coordinator extends User {
}
