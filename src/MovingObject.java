import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


public class MovingObject extends ScreenObject {
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
	
	public void move(){
		// TODO
	}
	
	public void collide(MovingObject o){
		// TODO
	}
	
	public void destuct(ScreenObject sc){
		// TODO
	}
}
