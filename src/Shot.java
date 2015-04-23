import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Shot extends MovingObject {

	public Shot(Point l, Rectangle s) {
		super(l, s);
	}

	public Shot(Point l, Rectangle s, Image i) {
		super(l, s, i);
	}

	public void draw(Graphics g) {
		Graphics2D shotDraw = (Graphics2D) g;
		shotDraw.drawImage(image, location.x, location.y, size.width,
				size.height, null);
	}
}
