import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/**
 * 
 */

/**
 * @author guinnc
 *
 */
public class MovingScreenObject extends ScreenObject {

	MyVector vector;
	Image myImage;
	
	/**
	 * @param location
	 * @param size
	 */
	public MovingScreenObject(Point location, Rectangle size, Image i) {
		super(location, size);
		myImage = i;
		// TODO Auto-generated constructor stub
	}
	
	// abstract?
	public void move() {
		
		
		location.x += vector.getChangeX();
		location.y += vector.getChangeY();
		
		if (location.x > Screen.screenWidth) {
			location.x -= Screen.screenWidth;
		}
		if (location.x < 0) {
			location.x += Screen.screenWidth;
		}
		
		if (location.y > Screen.screenHeight) {
			location.y -= Screen.screenHeight;
		}
		if (location.y < 0) {
			location.y += Screen.screenHeight;
		}
		
	}
	
	public void collide(MovingScreenObject otherObj) {
		
	}
	
	public void destruct() {
		
	}
	
	public void draw(Graphics g) {
		// reminder ... later we want to rotate image by
		// changeX and changeY
		Graphics2D g2 = (Graphics2D) g;
	if (this instanceof Ship) {
			Ship ship = (Ship) this;

			AffineTransform identity = new AffineTransform();
			AffineTransform trans = new AffineTransform();
			trans.setTransform(identity);
			trans.translate(location.x, location.y);
			trans.scale(0.25, 0.25);
	
			trans.rotate( Math.toRadians(ship.getAngle()),
					myImage.getWidth(null)/2,
					myImage.getHeight(null)/2);


			g2.drawImage(myImage, trans, null);
		}
		else {
			g2.drawImage(myImage, location.x, location.y,
					size.width, size.height,
					null);
		}
	}

	/**
	 * @return the vector
	 */
	public MyVector getVector() {
		return vector;
	}

	/**
	 * @param vector the vector to set
	 */
	public void setVector(MyVector vector) {
		this.vector = vector;
	}

	/**
	 * @return the myImage
	 */
	public Image getMyImage() {
		return myImage;
	}

	/**
	 * @param myImage the myImage to set
	 */
	public void setMyImage(Image myImage) {
		this.myImage = myImage;
	}

}
