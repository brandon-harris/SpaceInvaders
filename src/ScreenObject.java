import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class ScreenObject {
	protected Rectangle size;
	protected Point location;
	protected Image image;

	public ScreenObject(Point l, Rectangle s) {
		location = l;
		size = s;
	}

	public ScreenObject(Point l, Rectangle s, Image i) {
		location = l;
		size = s;
		image = i;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return the size
	 */
	public Rectangle getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Rectangle s) {
		size = s;
	}

	abstract public void draw(Graphics g);

}
