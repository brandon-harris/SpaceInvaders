import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Keeps track of the number of lives the LaserCannon has. Integer is the number
 * of lives.
 * 
 * @author Kennon
 *
 */
public class Lives extends ScreenObject {
	private int numberOfLives;

	/**
	 * Creates lives at a certain location, size and integer that keeps up with
	 * the number of lives.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size
	 * @param n
	 *            is the number of lives
	 */
	public Lives(Point l, Rectangle s, int n) {
		super(l, s);
		numberOfLives = n;
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * To draw a show, it is rotated by its orientation.
	 * @param g The graphics object
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font(Messages.getString("Lives.0"), Font.PLAIN, 20)); //$NON-NLS-1$
		g2.setColor(Color.WHITE);
		if (numberOfLives == 0) {
			g2.drawString(Messages.getString("Lives.1"), location.x, location.y); //$NON-NLS-1$
		} else {
			g2.drawString(Messages.getString("Lives.2") + (numberOfLives - 1), //$NON-NLS-1$
					location.x, location.y);
		}
	}

	/**
	 * Gets the number of lives.
	 * 
	 * @return numberOfLives which is the number of lives
	 */
	public int getLife() {
		return numberOfLives;
	}

	/**
	 * Sets the number of lives.
	 * 
	 * @param numOfLife
	 *            is the number of lives to be set
	 */
	public void setLife(int numOfLife) {
		numberOfLives = numOfLife;
	}
}