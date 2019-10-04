import java.awt.*;
import javax.swing.ImageIcon;

public class GameCore {
	/*
	 * Simple abstract class used for testing. Subclasses should implement the draw() method.
	 */
	public abstract class GameCore{
		protected static final int FONT_SIZE = 24;
		
		private static final DisplayMode POSSIBLE_MODES[] = {
				new DisplayMode(800, 600, 32, 0),
				new DisplayMode(800, 600, 24, 0),
				new DisplayMode(800, 600, 16, 0),
				new DisplayMode(640, 480, 32, 0),
				new DisplayMode(640, 480, 32, 0),
				new DisplayMode(640, 480, 32, 0)
		};
		
		private boolean isRunning;
		protected ScreenManager screen;
		
		/*
		 * Signals the game loop that is time to quit
		 */
		public void stop() {
			isRunning = false;
		}
		
		/*
		 * Calls init() and gameLoop()
		 */
		public void run() {
			try {
				init();
				gameLoop();
			}
			finally {
				screen.restoreScreen();
			}
		}
		
		/*
		 * Sets full screen mode and initiates and objects.
		 */
		
	}
}
