import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 */

/**
 * @author guinnc
 *
 */
public class Screen extends JPanel implements KeyListener {

	public static int screenWidth = 600;
	public static int screenHeight = 500;
	public static ImageIcon backgroundImg = new ImageIcon("space.jpg");
	public static ImageIcon asteroidImg = new ImageIcon("SingleAsteroid.gif");
	public static ImageIcon shipImg = new ImageIcon("PlayerShip.gif");
	
	private ArrayList<ScreenObject> screenObjects;
	private javax.swing.Timer timer;
	
	private Ship playerShip;
	
	/**
	 * The screen has a black background.  
	 */
	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		screenObjects = new ArrayList<ScreenObject>();
		Random generator = new Random();
		// for loop to add asteroids
		for (int count = 0; count < 2; count++) {
			// generate random location
			int x = generator.nextInt(screenWidth);
			int y = generator.nextInt(screenHeight);
			// generate size
			int width = 80;
			int height = 80; // trial sizes 
			
			int points = 10; // trial points
			
			Asteroid asteroid = new Asteroid(new Point(x, y),
					new Rectangle(width, height),
					points,
					asteroidImg.getImage());
			
			// generate trial vector
			double dx = 10*Math.random() - 5;
			double dy = 10*Math.random() - 5;
			asteroid.setVector(new MyVector(dx, dy));
			
			screenObjects.add(asteroid);

		} // finished adding asteroids
		
		// add player's ship
		int x = screenWidth/2 - 10;
		int y = screenHeight/2;
		playerShip = new Ship(new Point(x, y),
				new Rectangle(20,20),
				shipImg.getImage());
		playerShip.setVector(new MyVector(0,0));
		playerShip.setAngle(0);
		screenObjects.add(playerShip);
		

		this.addKeyListener(this);
		
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		
		super.paintComponent(g);
		//g.drawImage(backgroundImg.getImage(), 
		//		0, 0, screenWidth, screenHeight, null);
		
		// draw objects
		for (ScreenObject obj : screenObjects) {
			obj.draw(g);
		}
	}

	/**
	 * @return the timer
	 */
	public javax.swing.Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(javax.swing.Timer timer) {
		this.timer = timer;
	}
	
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			// move each object
			for (ScreenObject obj: screenObjects) {
				if (obj instanceof MovingScreenObject) {
					MovingScreenObject movingObj =
							(MovingScreenObject) obj;
					movingObj.move();
				}
			}
			
			repaint();
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		   int keyCode = event.getKeyCode();
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		            // handle up 
		            break;
		        case KeyEvent.VK_DOWN:
		            // handle down 
		            break;
		        case KeyEvent.VK_LEFT:
		        	double newAngle = playerShip.getAngle() + 5;
		        	if (newAngle > 360) {
		        		newAngle -= 360;
		        	}
		            playerShip.setAngle(newAngle);
		            
		            break;
		        case KeyEvent.VK_RIGHT :
		        	newAngle = playerShip.getAngle() - 5;
		        	if (newAngle < 0) {
		        		newAngle += 360;
		        	}
		            playerShip.setAngle(newAngle);
		            
		            break;
		     }
		    repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
