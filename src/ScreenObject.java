import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * ScreenObjects have a size, location and image.
 * 
 * @author Kennon
 *
 */
public abstract class ScreenObject {
	protected Rectangle size;
	protected Point location;
	protected Image image;

	/**
	 * Creates a screenobject with a particular location and size.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size.
	 */
	public ScreenObject(Point l, Rectangle s) {
		location = l;
		size = s;
	}

	/**
	 * Creates a screenobject with a particular location, size, and image.
	 * 
	 * @param l
	 *            is the location
	 * @param s
	 *            is the size.
	 * @oaram i is the image.
	 */
	public ScreenObject(Point l, Rectangle s, Image i) {
		location = l;
		size = s;
		image = i;
	}

	/**
	 * All subclasses must have code to describe how the object should be drawn.
	 * 
	 * @param g
	 *            The graphics object
	 */
	abstract public void draw(Graphics g);

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public Rectangle getSize() {
		return size;
	}

	/**
	 * sets the image.
	 * 
	 * @param i
	 *            the image to set
	 */
	public void setImage(Image i) {
		image = i;
	}

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the location to be set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * Sets the size.
	 * 
	 * @param size
	 *            the size to set
	 */
	public void setSize(Rectangle s) {
		size = s;
	}

}