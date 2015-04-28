import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Is either and enemy that moves and shoots at the LaserCannon. Or a mystery
 * ship that allows the LaserCannon a possibility of points.
 * 
 * @author Kennon
 *
 */
public class Enemy extends ShootingObject {
	/**
	 * Creates enemy with a specific location and size.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size
	 */
	public Enemy(Point l, Rectangle s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates enemy with a specific location and size.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size
	 * @param i
	 *            is the image
	 */
	public Enemy(Point l, Rectangle s, Image i) {
		super(l, s, i);
		// TODO Auto-generated constructor stub
	}
}