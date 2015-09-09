import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * keeps count of the score has a score.
 *
 * @author Kennon
 */
public class Score extends ScreenObject {
    private int score;

    /**
     * Creates a score at a certain location,size, and particular number for
     * score.
     *
     * @param l  is the location
     * @param s  is the size
     * @param sc is the score
     */
    public Score(Point l, Rectangle s, int sc) {
        super(l, s);
        score = sc;
    }

    @Override
    /**
     * To draw a show, it is rotated by its orientation.
     * @param g The graphics object
     */
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font(Messages.getString("Score.0"), Font.PLAIN, 20)); //$NON-NLS-1$
        g2.setColor(Color.WHITE);
        g2.drawString(Messages.getString("Score.1") + score, location.x, location.y); //$NON-NLS-1$

    }

    /**
     * Gets the score.
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score.
     *
     * @param s is the score to be set
     */

    public void setScore(int s) {
        score = s;
    }
}
