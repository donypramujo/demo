package com.jaxi.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("game_image")
public class GameImage extends  File {
}
