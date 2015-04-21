import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


/**
 * An Asteroid is a space object that moves in a straight line.  
 * If it collides with the player's ship, it destroys the ship.
 * A player can shoot the asteroid.
 * If the asteroid is large, it breaks into two smaller pieces.
 * If the asteroid is small, it disentegrates when shot.  
 * @author guinnc
 *
 */
public class Asteroid extends Enemy {

	/**
	 * Create an asteroid as a particular place, with a 
	 * particular size, a particular point value, and an image.
	 * @param p The location.
	 * @param s The size (width and height)
	 * @param pv The point value
	 * @param i The image
	 */	
	public Asteroid(Point p, Rectangle s, int pv, Image i) {
		super(p, s, pv, i);
	}

	/**
	 * What to do when the asteroid breaks up.
	 */
	public void breakUp() {
		
	}
}
