import javax.swing.JFrame;

/**
 * Makes a window.
 *
 * @author Kennon
 */
public class GameWindow extends JFrame {
    /**
     * Create the window.
     *
     * @param args Ignored
     */
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
    }

    /**
     * The window contains the game Screen.
     */
    public GameWindow() {
        setTitle(Messages.getString("GameWindow.0"));// test //$NON-NLS-1$
        Screen screen = new Screen();
        add(screen);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        screen.setFocusable(true);
        screen.requestFocusInWindow();
        setVisible(true);
    }
}