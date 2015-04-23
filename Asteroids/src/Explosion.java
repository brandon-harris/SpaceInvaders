import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;



/**
 * An explosion is occurs when two objects collide and at least
 * one is destroyed.
 * @author guinnc
 *
 */
public class Explosion extends MovingScreenObject {

	/**
	 * Create an Explosion as a particular place, with a 
	 * particular size, a particular point value, and an image.
	 * @param location The location.
	 * @param size The size (width and height)
	 * @param pv The point value
	 * @angle angle The angle of the object.  
	 */	
	public Explosion(Point location, Rectangle size, Image i, double angle) {
		super(location, size, i, angle);
		maxAge = 15;  // explosions only last 15 time clicks.  
	}

}
