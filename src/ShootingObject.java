import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Creates a Shooting Object.
 *
 * @author Kennon
 */
public class ShootingObject extends MovingObject {
    /**
     * Creates a shootingobject at a certain location and size.
     *
     * @param l is the location
     * @param s is the size
     */
    public ShootingObject(Point l, Rectangle s) {
        super(l, s);
    }

    /**
     * Creates a shootingobject at a certain location,size and image.
     *
     * @param l is the location
     * @param s is the size
     * @param i is the image
     */
    public ShootingObject(Point l, Rectangle s, Image i) {
        super(l, s, i);
    }

    /**
     * All subclasses must have code to describe how the object should be drawn.
     *
     * @param g The graphics object
     */
    @Override
    public void draw(Graphics g) {
    }
}