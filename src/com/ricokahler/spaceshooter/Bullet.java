package com.ricokahler.spaceshooter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet extends Entity {
	
	public static final int WIDTH = 10;
	
	private Direction direction;
	private Entity sender;
	private float speed;

	public Bullet(Direction direction, float x, float y, float speed, Entity sender) throws SlickException {
		super(new Image("/res/bullet.png"));
		this.direction = direction;
		this.sender = sender;
		this.x = x;
		this.y = y;
		this.speed = speed;
		hp = 1;
	}
	
	public void update(int delta) {
		switch (direction) {
		case UP:
			move(0f, -speed);
			break;
		default:
			break;
		}
		super.update(delta);
	}
	
	public Entity getSender() {
		return sender;
	}
}
