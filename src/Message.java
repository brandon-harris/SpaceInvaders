import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Class that displays text on a screen.
 * 
 * @author brandon250x
 *
 */
public class Message extends ScreenObject {
	private String messageText = "";

	public Message(Point l, Rectangle s) {
		super(l, s);

	}

	/**
	 * Constructor for the class. Very useful.
	 * 
	 * @param l
	 *            a point that the message will be set at.
	 * @param s
	 *            the rectangle size that the message must be set in.
	 * @param i
	 *            image that you can optionally add to the message rectangle.
	 */
	public Message(Point l, Rectangle s, Image i) {
		super(l, s, i);

	}

	@Override
	/**
	 * Draws the component.
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("OCR A Std", Font.PLAIN, 96));
		g2.setColor(Color.ORANGE);
		g2.drawString(messageText, location.x, location.y);
	}

	/**
	 * Gets the message stored here.
	 * 
	 * @return messageText the message text.
	 */
	public String getText() {
		return messageText;
	}

	/**
	 * Sets the message text.
	 * 
	 * @param s
	 *            the message to set.
	 */
	public void setText(String s) {
		messageText = s;
	}
}
