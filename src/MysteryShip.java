import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Ship that allows user for possibility of points. Has a pointvalue.
 *
 * @author Kennon
 */
public class MysteryShip extends MovingObject {
    protected int pointvalue;

    /**
     * Creates a MysterShip at a certain location and size.
     *
     * @param l is the location
     * @param s is the size
     */
    public MysteryShip(Point l, Rectangle s) {
        super(l, s);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a MysterShip at a certain location and size.
     *
     * @param l is the location
     * @param s is the size
     * @param i is the image
     */
    public MysteryShip(Point l, Rectangle s, int pv, Image i) {
        super(l, s, i);
        pointvalue = pv;
        // TODO Auto-generated constructor stub
    }

    /**
     * Draw the object by displaying the image.
     *
     * @param g Graphics object.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D mysterything = (Graphics2D) g;
        mysterything.drawImage(image, location.x, location.y, size.width,
                size.height, null);
    }

    /**
     * Retrieve the point value of the Enemy.
     *
     * @return the pointValue
     */
    public int getPointValue() {
        return pointvalue;
    }

    /**
     * Change the point value of the Enemy.
     *
     * @param pointValue the pointValue to set
     */
    public void setPointValue(int pointValue) {
        this.pointvalue = pointValue;
    }
}