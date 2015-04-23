import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


/**
 * A shot can be fired by a ship or a saucer.
 * @author guinnc
 *
 */
public class Shot extends MovingScreenObject {

	/**
	 * Create the Shot at a particular location and angle.
	 * @param location Starting location.
	 * @param size The shot's size.
	 * @param i The shot's image.
	 * @param angle The shot's orientation.
	 */
	public Shot(Point location, Rectangle size, Image i, double angle) {
		super(location, size, i, angle);
		
		// set up changeX and changeY
		// first assume the end point is 5 pixels to the right
		double endX = location.x + 5;
		double endY = location.y;

		
		double newEndX = 
				location.x + (endX -location.x)*Math.cos(Math.toRadians(angle));
		double newEndY =
				location.y + (endX - location.x)*Math.sin(Math.toRadians(angle));
		
		double changeX = (newEndX - location.x);
		double changeY = (newEndY - location.y);
		vector = new MyVector(changeX, changeY);

		maxAge = Screen.screenHeight/5;
	}
	
	/**
	 * To draw a show, it is rotated by its orientation.
	 * @param g The graphics object.
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		g2.setColor(Color.yellow);
		Rectangle2D.Double ray =
				new Rectangle2D.Double(location.x, location.y,
						size.getWidth(), size.getHeight());
		AffineTransform at = g2.getTransform();
		at.rotate(Math.toRadians(angle), location.x, location.y);
		g2.setTransform(at);
		g2.fill(ray);
		g2.setTransform(old);
		
	}

}
