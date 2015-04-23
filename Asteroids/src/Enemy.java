import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


/**
 * An Enemy is a space object that can destroy the players's ship.
 * It has a location, size, point value, and image.  
 * @author guinnc
 *
 */
public class Enemy extends MovingScreenObject {

	protected int pointValue;
	
	/**
	 * Create an Enemy as a particular place, with a 
	 * particular size, a particular point value, and an image.
	 * @param p The location.
	 * @param s The size (width and height)
	 * @param pv The point value
	 * @Image i The image.
	 */	
	public Enemy(Point p, Rectangle s, int pv, Image i) {
		super(p, s, i, 0);
		pointValue = pv;
		
	}

	/**
	 * Retrieve the point value of the Enemy.
	 * @return the pointValue 
	 */
	public int getPointValue() {
		return pointValue;
	}

	/**
	 * Change the point value of the Enemy.
	 * @param pointValue the pointValue to set
	 */
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	
}
