import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * @author guinnc
 *
 */
public abstract class ScreenObject {
	protected Point location;
	protected Rectangle size;
	
	/**
	 * Create the screen object with a particular location and size.
	 * @param location
	 * @param size
	 */
	public ScreenObject(Point location, Rectangle size) {
		super();
		this.location = location;
		this.size = size;
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
	
	// should this be abstract
	abstract public void draw(Graphics g);
		
}
