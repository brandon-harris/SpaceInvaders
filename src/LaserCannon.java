import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class LaserCannon extends ShootingObject {

	public LaserCannon(Point l, Rectangle s) {
		super(l, s);
	}

	public LaserCannon(Point l, Rectangle s, Image i) {
		super(l, s, i);
	}

	public void draw(Graphics g) {
		Graphics2D laserCanonDraw = (Graphics2D) g;
		laserCanonDraw.drawImage(image, location.x, location.y, size.width,
				size.height, null);
	}
}
