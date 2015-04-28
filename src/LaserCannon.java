import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Moved by player and shoots at enemy.
 * 
 * @author Kennon
 *
 */
public class LaserCannon extends ShootingObject {
	/**
	 * Creates a LaserCannon with a certain location and size.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size
	 */
	public LaserCannon(Point l, Rectangle s) {
		super(l, s);
	}

	/**
	 * Creates a LaserCannon with a certain location,size and image.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size
	 * @param i
	 *            is the image
	 */
	public LaserCannon(Point l, Rectangle s, Image i) {
		super(l, s, i);
	}

	/**
	 * To draw a show, it is rotated by its orientation.
	 * 
	 * @param g
	 *            The graphics object
	 */
	@Override
	public void draw(Graphics g) {
		Graphics2D laserCanonDraw = (Graphics2D) g;
		laserCanonDraw.drawImage(image, location.x, location.y, size.width,
				size.height, null);
	}
}