package com.ricokahler.spaceshooter;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public abstract class Entity {
	protected float x;
	protected float y;
	
	protected Shape bounds;
	protected Image image;
	private boolean isDestroyed;
	
	protected int hp;
	
	protected float speedX;
	protected float speedY;
	
	public Entity(Image image) {
		this.image = image;
		x = SpaceShooter.WIDTH / 2f - image.getWidth() / 2f;
		y = SpaceShooter.HEIGHT / 2f - image.getHeight() / 2f;
		bounds = new Ellipse(x, y,
				image.getWidth() / 2f, image.getHeight() / 2f);
		hp = 0;
	}
	
	public void move(float speedX, float speedY) {
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw() {
		image.draw(x, y);
	}
	
	public void update(int delta) {
		x += speedX * delta;
		y += speedY * delta;
		bounds.setLocation(x, y);
		
		if (y < - image.getHeight()) destroy();
		if (y > SpaceShooter.HEIGHT + image.getHeight()) destroy();
		if (hp <= 0) destroy();
	}
	
	public void hit(Entity scrub) {
		if(!scrub.isDestroyed() && !this.isDestroyed())
			hp--;
	}
	
	public void setBounds(Shape bounds) { this.bounds = bounds; }
	public Shape getBounds() { return bounds; }
	
	public void destroy() { isDestroyed = true; }
	public boolean isDestroyed() { return isDestroyed; }
	
	public void setHP(int hp) { this.hp = hp; }
	public int getHP() { return hp; }
}
