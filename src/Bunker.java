import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;


public class Bunker extends ScreenObject {
	private int hits;
	final private static int BUNKER_DIMENSION_WIDTH = 88;
	final private static int BUNKER_DIMENSION_HEIGHT = 64;
	
	public Bunker(int h, Point l, Rectangle s, Image i) {
		super(l,s, i);
		hits = h;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D bunker = (Graphics2D) g;
		bunker.drawImage(image, location.x, location.y, size.width, size.height, null);
	}

	/**
	 * @return the hits
	 */
	public int getHits() {
		return hits;
	}

	/**
	 * @param hits the hits to set
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}

	public static int getBunkerDimensionWidth() {
		return BUNKER_DIMENSION_WIDTH;
	}
	
	public static int getBunkerDimensionHeight(){
		return BUNKER_DIMENSION_HEIGHT;
	}
	

}
