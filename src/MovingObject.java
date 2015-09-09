import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * A MovingObject has a Vector (Change X and ChangeY).
 *
 * @author Kennon
 */
public class MovingObject extends ScreenObject {
    MyVector vector;

    /**
     * Creates a MovingObject at a particular location, size, and image.
     *
     * @param l The starting location.
     * @param s The starting size.
     */
    public MovingObject(Point l, Rectangle s) {
        super(l, s);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a MovingObject at a particular location, size, and image.
     *
     * @param l The starting location.
     * @param s The starting size.
     * @param i The object's image.
     */
    public MovingObject(Point l, Rectangle s, Image i) {
        super(l, s, i);
        // TODO Auto-generated constructor stub
    }

    /**
     * Returns true if the objects collide.
     *
     * @param o The other moving object.
     * @return True if there is a collision; false, otherwise.
     */
    public boolean collide(ScreenObject o) {
        Rectangle otherR = o.getSize();
        otherR.setLocation(o.getLocation());
        this.getSize().setLocation(this.getLocation());
        return (otherR.intersects(this.getSize()));
    }

    /**
     * Destruction of the image once it has collision.
     *
     * @param sc ScreenObject
     */
    public void destruct(ScreenObject sc) {
        // TODO
    }

    /**
     * Draw the object by displaying the image.
     *
     * @param g Graphics object.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D movingThing = (Graphics2D) g;
        movingThing.drawImage(image, location.x, location.y, size.width,
                size.height, null);
    }

    /**
     * Retrieve the vector.
     *
     * @return the vector
     */
    public MyVector getVector() {
        return vector;
    }

    /**
     * To move an object,adds its vector to its location.
     *
     * @param object Passes in a object.
     */
    public void move(MovingObject object) {
        object.location.x += object.vector.getChangeX();
        object.location.y += object.vector.getChangeY();
    }

    /**
     * Sets the vector.
     *
     * @param v the vector to set
     */
    public void setVector(MyVector v) {
        vector = v;
    }
}