import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;



/**
 * A Ship is the player controlled object that has a particular
 * location, size, image, and angle.  
 * @author guinnc
 *
 */
public class Ship extends MovingScreenObject {


	/**
	 * Create the ship at a location.
	 * @param location Starting location.
	 * @param size Starting size.
	 * @param i Image of the ship.
	 */
	public Ship(Point location, Rectangle size, Image i) {
		super(location, size, i, 0);
		angle = 90;
		
	}


}
