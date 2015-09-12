import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * An Alien can kill the LaserCannon by shooting it. It has a point value.
 *
 * @author Kennon
 */
public class Alien extends ShootingObject {
    protected int pointValue;

    /**
     * Creates Alien that has a particular location,size, pointValue, image.
     *
     * @param l  the location
     * @param s  the size
     * @param pv the pointValue
     * @param i  the image
     */
    public Alien(Point l, Rectangle s, int pv, Image i) {
        super(l, s, i);
        pointValue = pv;
    }

    /**
     * Draw the object by displaying the image.
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D movingThing = (Graphics2D) g;
        movingThing.drawImage(image, location.x, location.y, size.width,
                size.height, null);
    }

    /**
     * Gets the pointValue.
     *
     * @return the pointValue
     */
    public int getPointValue() {
        return pointValue;
    }

    /**
     * Sets the pointValue.
     *
     * @param pointValue the pointValue to set
     */
    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
}