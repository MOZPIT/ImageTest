import java.awt.*;
import javax.swing.ImageIcon;

public class spriteTest1 {
	public static void main(String args[]) {
		spriteTest1 test = new spriteTest1();
		test.run();
	}
	
	private static final DisplayMode POSSIBLE_MODES[] = {
			new DisplayMode(800, 600, 32, 0),
			new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0)
	};
	
	private static final long DEMO_TIME = 10000;
	
	private ScreenManager screen;
	private Image bgImage;
	private Sprite sprite;
	private Sprite sprites[];
	
	public void loadImages() {
		//load images
		bgImage = loadImage("images/background.jpg");
		Image player1 = loadImage("images/player1.png");
		Image player2 = loadImage("images/player2.png");
		Image player3 = loadImage("images/player3.png");
		
		//create a sprite
		Animation anim = new Animation();
		anim.addFrame(player1, 250);
		anim.addFrame(player2, 150);
		anim.addFrame(player1, 150);
		anim.addFrame(player2, 150);
		anim.addFrame(player3, 200);
		anim.addFrame(player2, 150);
		sprite = new Sprite(anim);
		//start the sprite off moving down and to the right
		sprite.setVelocityX(0.2f);
		sprite.setVelocityY(0.2f);
	}
	
	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
	
	public void run() {
		screen = new ScreenManager();
		try {
			DisplayMode displayMode = screen.findFirstCompatibleMode(POSSIBLE_MODES);
			screen.setFullScreen(displayMode);
			loadImages();
			animationLoop();
		}
		finally {
			screen.restoreScreen();
		}
	}
	
	public void animationLoop() {
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		
		while(currTime - startTime < DEMO_TIME) {
			long elapsedTime = System.currentTimeMillis() - currTime;
			currTime += elapsedTime;
			
			//upadte the sprites
			update(elapsedTime);
			
			//draw the update on the screen
			Graphics2D g = screen.getGraphics();
			draw(g);
			g.dispose();
			screen.update();
			
			//take a nap
			try {
				Thread.sleep(20);
			}
			catch (InterruptedException ex) { }
		}
	}
	
	public void update(long elapsedTime) {
		//check sprite bounds
		if(sprite.getX() < 0) {
			sprite.setVelocityX(Math.abs(sprite.getVelocityX()));
		}
		else if(sprite.getX() + sprite.getWidth() >= screen.getWidth()) {
			sprite.setVelocityX(-Math.abs(sprite.getVelocityX()));
		}
		
		if(sprite.getY() < 0) {
			sprite.setVelocityY(Math.abs(sprite.getVelocityY()));
		}
		else if(sprite.getY() + sprite.getHeight() >= screen.getHeight()) {
			sprite.setVelocityY(-Math.abs(sprite.getVelocityY()));
		}
		
		//update sprite
		sprite.update(elapsedTime);
	}
	
	public void draw(Graphics g) {
		//draw background
		g.drawImage(bgImage,  0,  0,  null);
		
//		for(int i = 0; i<sprites.length; i++) {
//			sprites[i].update(elapsedTime);
//			g.drawImage(sprites[i].getImage(),
//					Math.round(sprites[i].getX()),
//					Math.round(sprites[i].getY()),
//					null);
//		}
		//draw sprite
		g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
	}
}
