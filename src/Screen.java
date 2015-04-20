import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Screen extends JPanel implements KeyListener {
	public static int screenWidth = 600;
	public static int screenHeight = 500;
	
	public static ImageIcon enemyType1Frame1 = new ImageIcon("Enemy1 (frame1).png");
	public static ImageIcon enemyType1Frame2 = new ImageIcon("Enemy1 (frame2).png");
	public static ImageIcon enemyType2Frame1 = new ImageIcon("Enemy2 (frame1).png");
	public static ImageIcon enemyType2Frame2 = new ImageIcon("Enemy2 (frame2).png");
	public static ImageIcon enemyType3Frame1 = new ImageIcon("Enemy3 (frame1).png");
	public static ImageIcon enemyType3Frame2 = new ImageIcon("Enemy3 (frame2).png");
	public static ImageIcon laserCanon = new ImageIcon("LaserCanon.png");
	public static ImageIcon bunker = new ImageIcon("Bunker.png");
	
	//private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	
	
	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
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
}
