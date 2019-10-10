import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

/*
 * A simple mouselook test. Using mouselook, the user can virtually move the mouse in any direction indefinetely. Without mouselook, the mouse stops when it hits the edge of the screen.
 * <p>Mouselook works by recentering the mouse whenever it is moved, so it can always measure the relative mouse movement, and the mouse never hits the edge of the screen. 
 */
public class MouselookTest extends GameCore1 implements MouseMotionListener, KeyListener{
	
	public static void main(String[] args) {
		new MouselookTest().run();
	}
	
	private Image bgImage;
	private Robot robot;
	private Point mouseLocation;
	private Point centerLocation;
	private Point imageLocation;
	private boolean relativeMouseMode;
	private boolean isRecentering;
	
	public void init() {
		super.init();
		mouseLocation = new Point();
		centerLocation = new Point();
		imageLocation = new Point();
		relativeMouseMode = true;
		isRecentering = false;
		
	}
}
