import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


public class Lives extends ScreenObject {
	private int numberOfLives;
	
	public Lives(Point l, Rectangle s,int n) {
		super(l, s);
		numberOfLives = n;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("OCR A Std", Font.PLAIN, 20));
		g2.setColor(Color.WHITE);
		g2.drawString("Lives: " +numberOfLives, location.x, location.y);
	}
	public int getLife() {
		return numberOfLives;
	}
	public void setLife(int numOfLife) {
		numberOfLives = numOfLife;
	}

}
