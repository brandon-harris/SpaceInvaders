import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Screen extends JPanel implements KeyListener {
	public static int screenWidth = 1024;
	public static int screenHeight = 768;
	public static Score testScore = new Score(new Point(800,15), new Rectangle(10, 10, 10, 10), 99);
	public static ImageIcon enemyType1Frame1 = new ImageIcon("Enemy1 (frame1).png");
	public static ImageIcon enemyType1Frame2 = new ImageIcon("Enemy1 (frame2).png");
	public static ImageIcon enemyType2Frame1 = new ImageIcon("Enemy2 (frame1).png");
	public static ImageIcon enemyType2Frame2 = new ImageIcon("Enemy2 (frame2).png");
	public static ImageIcon enemyType3Frame1 = new ImageIcon("Enemy3 (frame1).png");
	public static ImageIcon enemyType3Frame2 = new ImageIcon("Enemy3 (frame2).png");
	public static ImageIcon redSaucer = new ImageIcon("Red Saucer.png");
	public static ImageIcon laserCanon = new ImageIcon("LaserCanon.png");
	public static ImageIcon bunker = new ImageIcon("Bunker.png");
	
	private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	
	
	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		screenObjects = new ArrayList<ScreenObject>();
		//System.out.println(testScore.getClass());
		//System.out.println(testScore.getScore());
		//testScore.setScore(44);
		//System.out.println(testScore.getScore());
		for(int x = 250; x < 800; x += 50){
			MovingObject enemy1_frame1 = new MovingObject(new Point(x, 100), new Rectangle(x, 10, 30, 30), enemyType1Frame1.getImage());
			screenObjects.add(enemy1_frame1);
			MovingObject enemy2_frame1 = new MovingObject(new Point(x, 150), new Rectangle(x, 10, 30, 30), enemyType2Frame1.getImage());
			screenObjects.add(enemy2_frame1);
			MovingObject enemy2_frame1_line2 = new MovingObject(new Point(x, 200), new Rectangle(x, 10, 30, 30), enemyType2Frame1.getImage());
			screenObjects.add(enemy2_frame1_line2);
			MovingObject enemy3_frame1 = new MovingObject(new Point(x, 250), new Rectangle(x, 10, 30, 30), enemyType3Frame1.getImage());
			screenObjects.add(enemy3_frame1);
			MovingObject enemy3_frame1_line2 = new MovingObject(new Point(x, 300), new Rectangle(x, 10, 30, 30), enemyType3Frame1.getImage());
			screenObjects.add(enemy3_frame1_line2);
		}
		MovingObject laserCanonPic = new MovingObject(new Point(100, 650), new Rectangle(10, 10, 50, 30), laserCanon.getImage());
		screenObjects.add(laserCanonPic);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void paintComponent(Graphics g){
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		super.paintComponent(g);
		testScore.draw(g);
		for (ScreenObject obj : screenObjects){
			obj.draw(g);
		}
	}
}
