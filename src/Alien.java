import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * A Alien can kill the LaserCannon by shooting it.
 * It has a pointvalue. 
 * @author Kennon
 *
 */
public class Alien extends ShootingObject {
 protected int pointvalue;
/**
 * Creates Alien that has a particular location,size, pointvalue, image. 
 * @param l the location
 * @param s the size
 * @param pv the pointvalue
 * @param i the image
 * 
 */
 public Alien(Point l, Rectangle s,int pv, Image i) {
  super(l, s, i);
  pointvalue = pv;
  // TODO Auto-generated constructor stub
 }
 /**
  * Draw the object by displaying the image.
  * @param Graphics g 
  */
 public void draw(Graphics g) {
  Graphics2D movingThing = (Graphics2D) g;
  movingThing.drawImage(image, location.x, location.y, size.width, size.height, null);
  }
 /**
  * Gets the pointvalue.
  * @return the pointvalue
  */
 public int getPointValue() {
  return pointvalue;
 }
 /** Sets the pointvalue.
  * @param pointValue the pointValue to set
  */
 public void setPointValue(int pointvalue) {
  this.pointvalue = pointvalue;
 }
}