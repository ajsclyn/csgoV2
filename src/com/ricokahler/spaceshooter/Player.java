package com.ricokahler.spaceshooter;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {

	//private Weapon weapon;
	private GameScene gameScene;
	private int fireTime;
	private int fireRate;
	
	public Player(GameScene gameScene) throws SlickException {
		super(new Image("/res/ship.png").getScaledCopy(0.25f));
		//weapon = new Weapon(gameScene, Direction.UP);
		this.gameScene = gameScene;
		fireTime = 0;
		fireRate = 200;
		hp = 10;
	}
	
	
	public void fire() {
		try {
			if (fireTime > fireRate) {
				fireTime = 0;
				gameScene.add(new Bullet(Direction.UP,
						x + image.getWidth() / 2 - Bullet.WIDTH / 2,
						y,
						0.3f,
						this));
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(int delta) {
		super.update(delta);
		fireTime += delta;
	}
}
