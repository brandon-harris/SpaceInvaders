import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements KeyListener {
	public static int screenWidth = 1024;
	public static int screenHeight = 768;
	public static Score displayScore = new Score(new Point(750, 25),
			new Rectangle(10, 10, 10, 10), 0);
	public static Lives displayLives = new Lives(new Point(175, 25),
			new Rectangle(10, 10, 10, 10), 2);
	public static ImageIcon enemyType1Frame1 = new ImageIcon(
			"Enemy1 (frame1).png");
	public static ImageIcon enemyType1Frame2 = new ImageIcon(
			"Enemy1 (frame2).png");
	public static ImageIcon enemyType2Frame1 = new ImageIcon(
			"Enemy2 (frame1).png");
	public static ImageIcon enemyType2Frame2 = new ImageIcon(
			"Enemy2 (frame2).png");
	public static ImageIcon enemyType3Frame1 = new ImageIcon(
			"Enemy3 (frame1).png");
	public static ImageIcon enemyType3Frame2 = new ImageIcon(
			"Enemy3 (frame2).png");
	public static ImageIcon redSaucer = new ImageIcon("Red Saucer.png");
	public static ImageIcon laserCanon = new ImageIcon("LaserCanon.png");
	public static ImageIcon bunker = new ImageIcon("Bunker.png");
	private ArrayList<Alien> alienObjects;
	private ArrayList<Bunker> bunkerObjects;
	private javax.swing.Timer timer;
	private LaserCannon laserCannonPic;
	private Shot shot;
	private MysteryShip mysteryShipPic;
		

	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		alienObjects = new ArrayList<Alien>();
		bunkerObjects = new ArrayList<Bunker>();

		for (int x = 250; x < 800; x += 50) {
			int points = 10;
			Alien enemy1_frame1 = new Alien(new Point(x, 100), new Rectangle(x,
					10, 30, 30), points, enemyType1Frame1.getImage());
			alienObjects.add(enemy1_frame1);
			Alien enemy2_frame1 = new Alien(new Point(x, 150), new Rectangle(x,
					10, 30, 30), points, enemyType2Frame1.getImage());
			alienObjects.add(enemy2_frame1);
			Alien enemy2_frame1_line2 = new Alien(new Point(x, 200),
					new Rectangle(x, 10, 30, 30), points,
					enemyType2Frame1.getImage());
			alienObjects.add(enemy2_frame1_line2);
			Alien enemy3_frame1 = new Alien(new Point(x, 250), new Rectangle(x,
					10, 30, 30), points, enemyType3Frame1.getImage());
			alienObjects.add(enemy3_frame1);
			Alien enemy3_frame1_line2 = new Alien(new Point(x, 300),
					new Rectangle(x, 10, 30, 30), points,
					enemyType3Frame1.getImage());
			alienObjects.add(enemy3_frame1_line2);
		}
		for (int b = 1; b < 5; b++) {
			Bunker bunker1 = new Bunker(5, new Point(b * 200, 550),
					new Rectangle(b * 50, 50, Bunker.getBunkerDimensionWidth(),
							Bunker.getBunkerDimensionHeight()),
					bunker.getImage());
			bunkerObjects.add(bunker1);

		}
		 
		for (Alien obj : alienObjects) {
			if (obj instanceof Alien) {
				obj.setVector(new MyVector(10, 0));
			}
		}
		
		laserCannonPic = new LaserCannon(new Point(500, 650), new
				 Rectangle(10, 10, 50, 30), laserCanon.getImage());

		Timer timer = new Timer(500, new TimerListener());
		timer.start();
		
		addKeyListener(this);
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			boolean flag = false;
			for (Alien obj : alienObjects) {
				if (obj.location.getX() > 800) {
					flag = true;
					for (Alien innerLoop : alienObjects) {
						innerLoop.setVector(new MyVector(0, 10));
					}
					for (Alien innerLoop : alienObjects){
						
					innerLoop.move(innerLoop);
					}
					for (Alien innerLoop : alienObjects){
						
						innerLoop.setVector(new MyVector(-10, 0));
					}
					for (Alien innerLoop : alienObjects){
						innerLoop.move(innerLoop);
					}
				}}
			for (Alien obj : alienObjects){
				
				if (obj.location.getX() < 200) {
					
					for (Alien innerLoop : alienObjects) {
						innerLoop.setVector(new MyVector(0, 10));
						innerLoop.move(innerLoop);	
						innerLoop.setVector(new MyVector(10, 0));
						innerLoop.move(innerLoop);
					}
				}
				obj.move(obj);
			}
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();
		super.paintComponent(g);
		displayScore.draw(g);
		displayLives.draw(g);
		for (ScreenObject obj : alienObjects) {
			obj.draw(g);
		}
		for (Bunker bunkerzzz : bunkerObjects) {
			bunkerzzz.draw(g);
		}
		laserCannonPic.draw(g);
		int points = 100;
		mysteryShipPic = new MysteryShip (new Point(900,50), new Rectangle (10,10,50,30), points, redSaucer.getImage());
		mysteryShipPic.draw(g);

	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			laserCannonPic.setVector(new MyVector(-5,0));
			laserCannonPic.move(laserCannonPic);
			break;
		case KeyEvent.VK_RIGHT:
			laserCannonPic.setVector(new MyVector(5,0));
			laserCannonPic.move(laserCannonPic);
			break;
		case KeyEvent.VK_SPACE: {
			Point p = laserCannonPic.getLocation();
			Rectangle r = laserCannonPic.getSize();
			//Shot shot = new Shot(new Point(p.x, r.width/2, p.y + r.height / 2, new Rectangle(15, 2),shot);
				
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//empty on purpose
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//empty on purpose
	}
}