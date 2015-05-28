package com.ricokahler.spaceshooter;

import org.newdawn.slick.Image;

public class Enemy extends Entity {

	public Enemy(Image image, int hp, float speed, float x, float y) {
		super(image);
		this.hp = hp;
		this.speedY = speed;
		this.x = x;
		this.y = y;
	}
	
	public void update(int delta) {
		super.update(delta);
		//System.out.println(hp);
	}
}
