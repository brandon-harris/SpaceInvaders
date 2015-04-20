import javax.swing.JFrame;

/**
 * 
 */

/**
 * @author guinnc
 *
 */
public class GameWindow extends JFrame {

	/**
	 * The window contains the game Screen.
	 */
	public GameWindow() {
		setTitle("Asteroids");
		Screen screen = new Screen();
		add(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

		screen.setFocusable(true);
		screen.requestFocusInWindow();
		
		setVisible(true);
		
	}
	/**
	 * Create the window.  
	 * @param args Ignored.
	 */
	public static void main(String[] args) {
		GameWindow window = new GameWindow();

	}

}
