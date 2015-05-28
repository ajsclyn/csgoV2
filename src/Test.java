import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Test extends BasicGame {
	
	public static void main(String[] args) throws SlickException {
		Test game = new Test();
		AppGameContainer app = new AppGameContainer(game, 640, 480, false);
		app.start();
	}

	public Test() {
		super("IT WORKS!");
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawString("Hello World!", gc.getWidth() / 2, gc.getHeight() / 2);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
	
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
	
	}

}
