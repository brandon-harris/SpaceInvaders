import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * @author guinnc
 *
 */
public class Ship extends MovingScreenObject {

	private double angle;
	/**
	 * @param location
	 * @param size
	 * @param i
	 */
	public Ship(Point location, Rectangle size, Image i) {
		super(location, size, i);
		angle = 0;
		
	}
	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

}
