import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
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
	public static ImageIcon shotPic = new ImageIcon("Shot.png");
	public static ImageIcon laserCannonShot = new ImageIcon(
			"LaserCannonShot.png");
	private ArrayList<Alien> alienObjects;
	private ArrayList<Bunker> bunkerObjects;
	private ArrayList<Shot> multipleShots;
	private ArrayList<Shot> enemyShots;
	private ArrayList<ScreenObject> everythingList;
	private javax.swing.Timer timer;
	private javax.swing.Timer shotTimer;
	private javax.swing.Timer enemyShotTimer;
	// private javax.swing.Timer mysterymovTimer;
	private javax.swing.Timer startMystery;
	private javax.swing.Timer enemyShotTimerPartTwo;
	private LaserCannon laserCannon;
	private Shot shot;
	private static boolean shotSwitch = false;
	private static boolean enemyShotSwitch = false;
	private MysteryShip mysteryShipPic;
	private Random generator;
	private int turnaround;

	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);

		// MAKES ARRAY LISTS HERE
		alienObjects = new ArrayList<Alien>(); // aliens
		bunkerObjects = new ArrayList<Bunker>();
		multipleShots = new ArrayList<Shot>(); // laser cannon
		enemyShots = new ArrayList<Shot>(); // alien shots
		everythingList = new ArrayList<ScreenObject>(); // everything...

		// MAKE TIMERS HERE
		timer = new Timer(500, new TimerListener());
		enemyShotTimer = new Timer(3000, new EnemyShotTimerListener());
		enemyShotTimerPartTwo = new Timer(35,
				new EnemyShotTimerListenerPartTwo());
		shotTimer = new Timer(25, new ShotTimerListener());

		// KEEPS ALIEN BLOCK ON SCREEN
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

		// CREATES 4 BUNKERS
		for (int b = 1; b < 5; b++) {
			Bunker bunker1 = new Bunker(5, new Point(b * 200, 550),
					new Rectangle(b * 50, 50, Bunker.getBunkerDimensionWidth(),
							Bunker.getBunkerDimensionHeight()),
					bunker.getImage());
			bunkerObjects.add(bunker1);

		}
		// INITIAL MOVEMENT?
		for (Alien obj : alienObjects) {
			if (obj instanceof Alien) {
				obj.setVector(new MyVector(10, 0));
			}
		}

		// LASER CANNON CREATION
		laserCannon = new LaserCannon(new Point(500, 650), new Rectangle(10,
				10, 50, 30), laserCanon.getImage());
		// MYSTERY SHIP
		int points = 100;
		mysteryShipPic = new MysteryShip(new Point(1025, 50), new Rectangle(10,
				10, 50, 30), points, redSaucer.getImage());

		everythingList.addAll(alienObjects);
		everythingList.addAll(bunkerObjects);
		everythingList.add(mysteryShipPic);
		everythingList.add(laserCannon);

		// START TIMERS HERE AND KEY LISTENER
		timer.start();
		enemyShotTimer.start();

		// MYSTER SHIP RANDOM GENERATION
		generator = new Random();
		int randRange = 1 + generator.nextInt(15000);
		startMystery = new Timer(randRange, new startMysListener());
		startMystery.start();

		addKeyListener(this);
	}

	public void collideObjects() {
		for (ScreenObject obj : everythingList) {
			if (obj instanceof Alien) {
				for (ScreenObject nestedLoop : everythingList) {
					if (nestedLoop instanceof Shot) {
						Shot shotObject = (Shot) nestedLoop;
						if (shotObject.vector != null) {
							if (shotObject.vector.getChangeY() == -7) {
								if (shotObject.collide((MovingObject) obj) == true) {
									alienObjects.remove(obj);
								}
							}
						}
					}
				}
			}
		}
	}

	// MYSTERY SHIP TIMER (GETS GOING LEFT)
	private class startMysListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Timer mysterymovTimer = new Timer(100, new MysterymovListener());
			mysteryShipPic.setVector(new MyVector(-10, 0));
			turnaround = generator.nextInt(2500);
			turnaround = 0 - turnaround;
			mysterymovTimer.start();
			startMystery.stop();
		}
	}

	// MYSTER SHIP TIMER (BACK AND FORTH)
	private class MysterymovListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (mysteryShipPic.location.getX() <= turnaround) {
				mysteryShipPic.setVector(new MyVector(10, 0));
			}
			mysteryShipPic.move(mysteryShipPic);
			repaint();
			int randRange = 1024 + generator.nextInt(4000);
			if (mysteryShipPic.location.getX() > randRange) {
				mysteryShipPic.setVector(new MyVector(-10, 0));
			}
			mysteryShipPic.move(mysteryShipPic);
			repaint();
		}
	}

	// TODO RANDOM ALIEN LISTENER (always makes it through but does not show up
	// on screen)
	private class EnemyShotTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			enemyShotSwitch = true;
			Random rand = new Random();
			System.out.println(alienObjects.size());
			Alien shooterAlien = alienObjects.get(rand.nextInt(alienObjects
					.size() - 1));
			Point p = shooterAlien.getLocation();
			Rectangle r = shooterAlien.getSize();
			shot = new Shot(new Point(p.x + r.width / 2 - 5, p.y + 20),
					new Rectangle(p.x + r.width / 2 - 5, p.y + 20, 10, 15),
					shotPic.getImage());
			enemyShots.add(shot);
			everythingList.add(shot);
			enemyShotTimerPartTwo.start();
		}
	}

	private class EnemyShotTimerListenerPartTwo implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			for (Shot shot : enemyShots) {
				shot.setVector(new MyVector(0, 5));
				shot.move(shot);
				collideObjects();
			}
		}
	}

	// LASER CANNON SHOT LISTENER
	private class ShotTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			collideObjects();
				repaint();
				for (int x = 0; x < multipleShots.size() - 1; x++) {
				multipleShots.get(x).setVector(new MyVector(0, -7));
				multipleShots.get(x).move(multipleShots.get(x));
				if (multipleShots.get(x).location.getY() < 10){
					multipleShots.remove(x);
				}
				
			}
		}
	}

	// MOVES ALIENS/CONTROL DIRECTION
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			boolean flag = false;
			for (Alien obj : alienObjects) {
				if (obj.location.getX() > 800) {
					flag = true;
					for (Alien innerLoop : alienObjects) {
						innerLoop.setVector(new MyVector(0, 10));
					}
					for (Alien innerLoop : alienObjects) {

						innerLoop.move(innerLoop);
						collideObjects();
					}
					for (Alien innerLoop : alienObjects) {

						innerLoop.setVector(new MyVector(-10, 0));
					}
					for (Alien innerLoop : alienObjects) {
						innerLoop.move(innerLoop);
						collideObjects();
					}
				}
			}
			for (Alien obj : alienObjects) {

				if (obj.location.getX() < 200) {

					for (Alien innerLoop : alienObjects) {
						innerLoop.setVector(new MyVector(0, 10));
						innerLoop.move(innerLoop);
						collideObjects();
						innerLoop.setVector(new MyVector(10, 0));
						innerLoop.move(innerLoop);
						collideObjects();
					}
				}
				obj.move(obj);
				collideObjects();
			}
			repaint();
		}
	}

	// DRAW SCORE, LIVES, LASER CANNON, MYSTERYSHIP, SHOTS
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
		laserCannon.draw(g);
		mysteryShipPic.draw(g);
		if (shotSwitch == true) {
			for (Shot shot : multipleShots) {
				shot.draw(g);
			}
			if (enemyShotSwitch == true) { // TODO shows up on screen but not
											// moving yet
				for (Shot shot : enemyShots) {
					shot.draw(g);
				}
			}
		}

	}

	// KEYBOARD INPUT CONTROL
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			laserCannon.setVector(new MyVector(-5, 0));
			laserCannon.move(laserCannon);
			collideObjects();
			break;
		case KeyEvent.VK_RIGHT:
			laserCannon.setVector(new MyVector(5, 0));
			laserCannon.move(laserCannon);
			collideObjects();
			break;
		case KeyEvent.VK_SPACE: { // FIRE LASER CANNON
			Point p = laserCannon.getLocation();
			Rectangle r = laserCannon.getSize();
			// System.out.println(p + " " + r); test print
			shot = new Shot(new Point(p.x + r.width / 2 - 3, p.y - 20),
					new Rectangle(500, 650, 5, 15), laserCannonShot.getImage());
			shotSwitch = true;
			multipleShots.add(shot);
			everythingList.add(shot);
			// for (ScreenObject ss : everythingList) {
			// System.out.println(ss);
			// }
			shotTimer.start();
		}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// empty on purpose
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// empty on purpose
	}

}