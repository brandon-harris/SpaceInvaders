import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


public class Alien extends ShootingObject {
	protected int pointvalue;

	
	public Alien(Point l, Rectangle s,int pv, Image i) {
		super(l, s, i);
		pointvalue = pv;
		// TODO Auto-generated constructor stub
	}
	public int getPointValue() {
		return pointvalue;
	}

	/**
	 * @param pointValue the pointValue to set
	 */
	public void setPointValue(int pointvalue) {
		this.pointvalue = pointvalue;
	}

}
