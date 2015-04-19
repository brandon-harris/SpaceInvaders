import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class Screen extends JPanel implements KeyListener {
	public static int screenWidth = 750;
	public static int screenHeight = 750;
	
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
