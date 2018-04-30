package com.jaxi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("asm")
public class AreaSalesManager extends User {
}
