import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Gives the laserCannon protection and makes it harder for the Aliens to kill
 * lasercannon.
 * 
 * @author Kennon Has hits to know the current state of the bunker.
 *         BUNKER_DIMENSION_WIDTH,BUNKER_DIMENSION_HEIGHT, sets the size of the
 *         bunker.
 */
public class Bunker extends ScreenObject {
	private int hits = 0;
	final private static int BUNKER_DIMENSION_WIDTH = 88;
	final private static int BUNKER_DIMENSION_HEIGHT = 64;

	/**
	 * Creates a bunker with a certain location, size and image. Has integer to
	 * keep up with relative damage.
	 * 
	 * @param h
	 *            is the relative damage
	 * @param l
	 *            is the location
	 * @param s
	 *            is the point
	 * @param i
	 *            is the image
	 */
	public Bunker(int h, Point l, Rectangle s, Image i) {
		super(l, s, i);
		hits = h;
	}

	@Override
	/**
	 * To draw a show, it is rotated by its orientation.
	 * @param g The graphics object
	 */
	public void draw(Graphics g) {
		Graphics2D bunker = (Graphics2D) g;
		bunker.drawImage(image, location.x, location.y, size.width,
				size.height, null);
	}

	/**
	 * Gets the hit.
	 * 
	 * @return hits the hits
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * Sets the Hit.
	 * 
	 * @param hits
	 *            the hits to set
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

	/**
	 * Gets the BUnkerDimensionwidth
	 * 
	 * @return BUNKER_DIMENSION_WIDTH
	 */
	public static int getBunkerDimensionWidth() {
		return BUNKER_DIMENSION_WIDTH;
	}

	/**
	 * Get the BunkerDimensionHeight.
	 * 
	 * @return BUNKER_DIMENSION_HEIGHT
	 */
	public static int getBunkerDimensionHeight() {
		return BUNKER_DIMENSION_HEIGHT;
	}

}
