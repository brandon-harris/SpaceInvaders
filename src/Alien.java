import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	public void draw(Graphics g) {
		Graphics2D movingThing = (Graphics2D) g;
		movingThing.drawImage(image, location.x, location.y, size.width, size.height, null);

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
