import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class MysteryShip extends MovingObject {
	protected int pointvalue;

	public MysteryShip(Point l, Rectangle s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	public MysteryShip(Point l, Rectangle s, int pv, Image i) {
		super(l, s, i);
		pointvalue = pv;
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		Graphics2D mysterything = (Graphics2D) g;
		mysterything.drawImage(image, location.x, location.y, size.width,
				size.height, null);
	}

	public int getPointValue() {
		return pointvalue;
	}

	public void setPointValue(int pointvalue) {
		this.pointvalue = pointvalue;
	}

}
