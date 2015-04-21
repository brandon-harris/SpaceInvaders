import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;



/**
 * Screen objects have a location and size.
 * @author guinnc
 *
 */
public abstract class ScreenObject {
	protected Point location;
	protected Rectangle size;
	
	/**
	 * Create the screen object with a particular location and size.
	 * @param location Starting location.
	 * @param size Starting size.
	 */
	public ScreenObject(Point location, Rectangle size) {
		super();
		this.location = location;
		this.size = size;
	}

	/**
	 * Retrieve the location.
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Change the location.
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * 
	 * Retrieve the size.
	 * @return the size
	 */
	public Rectangle getSize() {
		return size;
	}

	/**
	 * Change the size.
	 * @param size the size to set
	 */
	public void setSize(Rectangle size) {
		this.size = size;
	}
	
	/**
	 * All subclasses must have code to describe how
	 * the object should be drawn.
	 * @param g The graphics object.
	 */
	abstract public void draw(Graphics g);
		
}
