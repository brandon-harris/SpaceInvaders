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
public class Enemy extends MovingScreenObject {

	protected int pointValue;
	
	/**
	 * 
	 */
	public Enemy(Point p, Rectangle s, int pv, Image i) {
		super(p, s, i);
		pointValue = pv;
		
	}

	/**
	 * @return the pointValue
	 */
	public int getPointValue() {
		return pointValue;
	}

	/**
	 * @param pointValue the pointValue to set
	 */
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	
}
