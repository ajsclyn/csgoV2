package com.ricokahler.spaceshooter;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SpaceShooter extends StateBasedGame {
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	public static void main(String[] args) throws SlickException {
		SpaceShooter game = new SpaceShooter();
		AppGameContainer app = new AppGameContainer(game, WIDTH, HEIGHT, false);
		app.start();
	}

	
	public SpaceShooter() {
		super("Space Shooter");
	}

	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new GameScene());
	}
}
