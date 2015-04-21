import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


public class Score extends ScreenObject {
	private int score;
	
	public Score(Point l, Rectangle s, int sc) {
		super(l, s);
		score = sc;
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.drawString("" +score, location.x, location.y);
		
	}
	
	public void setScore(int s){
		score = s;
	}
	
	public int getScore(){
		return score;
	}

}
