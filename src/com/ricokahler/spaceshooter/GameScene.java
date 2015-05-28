package com.ricokahler.spaceshooter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameScene extends BasicGameState {
	
	public static final int ID = 0;
	private List<Entity> entities;
	private List<Entity> entitiesToRemove;
	
	private List<Entity> enemies;
	private List<Entity> playerBullets;
	private List<Entity> enemyBullets;
	
	private Player player;
	
	public GameScene() {
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		entities = new ArrayList<>();
		entitiesToRemove = new ArrayList<>();
		
		enemies = new ArrayList<>();
		playerBullets = new ArrayList<>();
		enemyBullets = new ArrayList<>();
		
		player = new Player(this);
		add(player);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for (Entity entity: entities) {
			if (!entity.isDestroyed()) {
				entity.draw();
				g.draw(entity.getBounds());
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		float playerSpeedY = 0f;
		float playerSpeedX = 0f;
		if (gc.getInput().isKeyDown(Input.KEY_UP)) { playerSpeedY -= 0.2f; }
		if (gc.getInput().isKeyDown(Input.KEY_DOWN)) { playerSpeedY += 0.2f; }
		if (gc.getInput().isKeyDown(Input.KEY_LEFT)) { playerSpeedX -= 0.2f; }
		if (gc.getInput().isKeyDown(Input.KEY_RIGHT)) { playerSpeedX += 0.2f; }
		player.move(playerSpeedX, playerSpeedY);
		
		if (gc.getInput().isKeyDown(Input.KEY_SPACE)) { player.fire(); }
		if (gc.getInput().isKeyPressed(Input.KEY_G)) {
			add(new Enemy(new Image("/res/space-invaders-hi.png").getScaledCopy(.1f), 4, 0.03f,
					SpaceShooter.WIDTH/2 - 50, 0));
		}
		
		
		enemies.clear();
		playerBullets.clear();
		enemyBullets.clear();
		
		entitiesToRemove.clear();
		
		for (Entity entity: entities) {
			if (!entity.isDestroyed()) entity.update(delta);
			else entitiesToRemove.add(entity);
			if (entity instanceof Enemy) {
				enemies.add((Enemy)entity);
			} else if (entity instanceof Bullet
					&& ((Bullet)entity).getSender() instanceof Player) {
				playerBullets.add((Bullet)entity);
			} else if (entity instanceof Bullet
					&& ((Bullet)entity).getSender() instanceof Enemy) {
				enemyBullets.add((Bullet)entity);
			}
		}
		
		// clear entities
		for (Entity entity: entitiesToRemove) {
			entities.remove(entity);
		}
		
		handleCollisions(playerBullets, enemies);
		//handleCollisions(enemyBullets, Arrays.asList(player));
		
		
	}
	
	public void handleCollisions(List<Entity> entList1, List<Entity> entList2) {
		Entity ent1;
		Entity ent2;
		for (int i = 0; i < entList1.size(); i++) {
			for (int j = 0; j < entList2.size(); j++) { // O(n^2) rekt
				ent1 = entList1.get(i);
				ent2 = entList2.get(j);
				if (ent1.getBounds().intersects(ent2.getBounds())) {
					System.out.println("entlist1 hits entlist2");
					System.out.println(entList1.size() + ", " + entList2.size());
						ent2.hit(ent1);
						ent1.hit(ent2);
				}
			}
		}
	}
	
	public void add(Entity entity) {
		entities.add(entity);
	}

	@Override
	public int getID() { return ID; }
}
