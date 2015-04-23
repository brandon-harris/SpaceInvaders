import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


public class MovingObject extends ScreenObject {
	MyVector vector;
	
	public MovingObject(Point l, Rectangle s) {
		super(l, s);
		// TODO Auto-generated constructor stub
	}

	public MovingObject(Point l, Rectangle s, Image i) {
		super(l, s, i);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
	Graphics2D movingThing = (Graphics2D) g;
	movingThing.drawImage(image, location.x, location.y, size.width, size.height, null);

	}
	
	
	public void move(MovingObject object){
		object.location.x += object.vector.getChangeX();
		object.location.y += object.vector.getChangeY();
	}
	
	public boolean collide(MovingObject o){
		Rectangle otherR = o.getSize();
		otherR.setLocation(o.getLocation());
		this.getSize().setLocation(this.getLocation());
		if (otherR.intersects(this.getSize())) {
			return true;
		}
		return false;
	}
	public MyVector getVector() {
		return vector;
	}

	/**
	 * @param vector the vector to set
	 */
	public void setVector(MyVector v) {
		vector = v;
	}

	public void destuct(ScreenObject sc){
		// TODO
	}
}
