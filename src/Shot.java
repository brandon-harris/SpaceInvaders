import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Creates a shot that can be shot by alien or lasercannon.
 * 
 * @author Kennon
 *
 */
public class Shot extends MovingObject {
	/**
	 * Creates a shot at a particular location,size, and image.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size
	 */
	public Shot(Point l, Rectangle s) {
		super(l, s);
	}

	/**
	 * Creates a shot at a particular location,size, and image.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size
	 * @param i
	 *            is the image
	 */
	public Shot(Point l, Rectangle s, Image i) {
		super(l, s, i);
	}

	/**
	 * To draw a show, it is rotated by its orientation.
	 * 
	 * @param g
	 *            The graphics object
	 */
	public void draw(Graphics g) {
		Graphics2D shotDraw = (Graphics2D) g;
		shotDraw.drawImage(image, location.x, location.y, size.width,
				size.height, null);
	}
}