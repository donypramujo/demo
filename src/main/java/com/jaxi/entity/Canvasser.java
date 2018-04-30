package com.jaxi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("c")
public class Canvasser extends User {
}
