import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/*
 * A simple mouse test. Draws a "Hello World!" message at the location of the cursor. Click to change to "trail mode" to draw several messages. Use the mouse wheel to change colors.
 */

public class MouseTest extends GameCore1 implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {

	public static void main(String[] args) {
		new MouseTest().run();
	}
	
	private static final int TRAIL_SIZE = 10;
	private static final Color[] COLORS = {
			Color.white, Color.black, Color.yellow, Color.magenta};
	private LinkedList<Point> trailList;
	private boolean trailMode;
	private int colorIndex;
	
	public void init() {
		super.init();
		trailList = new LinkedList<Point>();
		
		Window window = screen.getFullScreenWindow();
		window.addMouseListener(this);
		window.addMouseMotionListener(this);
		window.addMouseWheelListener(this);
	}
	
	public synchronized void draw(Graphics2D g) {
		int count = trailList.size();
		
		if(count > 1 && !trailMode) {
			count = 1;
		}
		
		Window window = screen.getFullScreenWindow();
		
		//draw background
		g.setColor(window.getBackground());
		g.fillRect(0,  0,  screen.getWidth(), screen.getHeight());
		
		//draw instructions
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(window.getForeground());
		g.drawString("MouseTest. Press Escape to exit.", 5, FONT_SIZE);
		
		//draw mouse trail
		for(int i=0; i<count; i++) {
			Point p = (Point)trailList.get(i);
			g.drawString("Hello World!", p.x, p.y);
		}
	}
	
	//From the MouseListener interface
	public void mousePressed(MouseEvent e) {
		trailMode = !trailMode;
	}
	
	//From the MouseListener interface
	public void mouseReleased(MouseEvent e) {
		//do nothing
	}
	
	//From the MouseListener interface
	public void mouseClicked(MouseEvent e) {
		//Called after mouse is released - ignore it
	}
	
	//From the MouseListener interface
	public void mouseEntered(MouseEvent e) {
		mouseMoved(e);
	}
	
	//From the MouseListener interface
	public void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}
	
	//From the MouseMotionListener interface
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	
	//From the MouseMotionListener interface
	public synchronized void mouseMoved(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		trailList.addFirst(p);
		while(trailList.size() > TRAIL_SIZE) {
			trailList.removeLast();
		}
	}
	
	//From the MouseWheelListener interface
	public void mouseWheelMoved(MouseWheelEvent e) {
		colorIndex = (colorIndex + e.getWheelRotation()) % COLORS.length;
		
		if(colorIndex < 0) {
			colorIndex+=COLORS.length;
		}
		Window window = screen.getFullScreenWindow();
		window.setForeground(COLORS[colorIndex]);
	}
	
	//From the KeyListener interface
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			//exit the program
			stop();
		}
	}
	
	//From the KeyListener interface
	public void keyReleased(KeyEvent e) {
		//do nothing
	}
	
	//From the KeyListener interface
	public void keyTyped(KeyEvent e) {
		//do nothing
	}
}
