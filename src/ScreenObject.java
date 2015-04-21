import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public abstract class ScreenObject {
	protected Rectangle size;
	protected Point location;
	
	public ScreenObject(Point l, Rectangle s) {
		location = l;
		size = s;
	}
	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
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
	 * @param size the size to set
	 */
	public void setSize(Rectangle size) {
		this.size = size;
	}
	
	abstract public void draw(Graphics g);
		
}

