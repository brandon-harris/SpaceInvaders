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
	public static ImageIcon bunkerhit1 = new ImageIcon("bunkerhit1.png");
	public static ImageIcon bunkerhit2 = new ImageIcon("bunkerhit2.png");
	public static ImageIcon bunkerhit3 = new ImageIcon("bunkerhit3.png");
	public static ImageIcon bunkerhit4 = new ImageIcon("bunkerhit4.png");
	private ArrayList<Alien> alienObjects;
	private ArrayList<Bunker> bunkerObjects;
	private ArrayList<Shot> multipleShots;
	private ArrayList<Shot> enemyShots;
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
	private static ImageIcon explodingPlayerShip = new ImageIcon(
			"ShipExplosion.png");
	private static Message lossMessage = new Message(new Point(300, 300),
			new Rectangle(300, 300, 600, 600));

	public Screen() {
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		lossMessage.setText("You have lost");
		// MAKES ARRAY LISTS HERE
		alienObjects = new ArrayList<Alien>(); // aliens
		bunkerObjects = new ArrayList<Bunker>();
		multipleShots = new ArrayList<Shot>(); // laser cannon
		enemyShots = new ArrayList<Shot>(); // alien shots

		// MAKE TIMERS HERE
		timer = new Timer(500, new TimerListener());
		enemyShotTimer = new Timer(3000, new EnemyShotTimerListener());
		enemyShotTimerPartTwo = new Timer(35,
				new EnemyShotTimerListenerPartTwo());
		shotTimer = new Timer(25, new ShotTimerListener());

		// Adds Aliens to screen.
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
			Bunker bunker1 = new Bunker(0, new Point(b * 200, 550),
					new Rectangle(b * 50, 50, Bunker.getBunkerDimensionWidth(),
							Bunker.getBunkerDimensionHeight()),
					bunker.getImage());
			bunkerObjects.add(bunker1);

		}
		// INITIAL MOVEMENT.
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

		// START TIMERS HERE AND KEY LISTENER
		timer.start();
		enemyShotTimer.start();

		// MYSTER SHIP RANDOM GENERATION
		generator = new Random();
		int randRange = 1 + generator.nextInt(15000);
		startMystery = new Timer(randRange, new startMysListener());
		startMystery.start();
		shotTimer.start();
		addKeyListener(this);
	}

	public void collideObjects() {
		// Aliens hitting player
		for (Shot shots : enemyShots) {
			if (shots.collide(laserCannon) == true) {
				shots.setLocation(new Point(-10, -10));
				laserCannon.setImage(explodingPlayerShip.getImage());
				if (displayLives.getLife() > 0) {
					displayLives.setLife(displayLives.getLife() - 1);
					// set a waiting period here
					laserCannon.setImage(laserCanon.getImage());
					laserCannon.setLocation(new Point(200, 650));
					if (displayLives.getLife() <= 0) {
						laserCannon.setLocation(new Point(-300, -300));
					}
				}
			}
		}

		// Player vs Mystery Ship
		for (Shot shots : multipleShots) {
			if (shots.collide(mysteryShipPic) == true) {
				mysteryShipPic.setSize(new Rectangle(0, 0, 0, 0));
				mysteryShipPic.setLocation(new Point(-300, -300));
				displayScore.setScore(displayScore.getScore() + 100);
				repaint();
			}
		}

		// Alien vs Bunker Ship
		for (Alien alienShip : alienObjects) {
			for (Bunker bunkerObj : bunkerObjects) {
				if (alienShip.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 0) {
					alienShip.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit1.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (alienShip.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 1) {
					alienShip.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit2.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (alienShip.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 2) {
					alienShip.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit4.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (alienShip.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 3) {
					alienShip.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit3.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (alienShip.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 4) {
					bunkerObj.setSize(new Rectangle(-10, -10, 0, 0));
					repaint();
				}
			}
		}

		// Alien Ship bodies vs player ship body
		for (Alien alienShip : alienObjects) {
			if (alienShip.collide(laserCannon)) {
				laserCannon.setImage(explodingPlayerShip.getImage());
				if (displayLives.getLife() > 0) {
					displayLives.setLife(displayLives.getLife() - 1);
					// set a waiting period here
					laserCannon.setImage(laserCanon.getImage());
					laserCannon.setLocation(new Point(500, 650));
					if (displayLives.getLife() <= 0) {
						laserCannon.setLocation(new Point(-300, -300));
					}
				}
			}
		}

		// Alien vs Player Shots
		for (Shot shotObj : multipleShots) {
			for (Alien alienObj : alienObjects) {
				if (shotObj.collide(alienObj) == true) {
					//alienObj.setLocation(new Point(-10, -10));
					alienObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					displayScore.setScore(displayScore.getScore() + 10);
					repaint();
				}
			}
		}
		// Aliens vs Bunkers
		for (Shot shotObj : enemyShots) { // check in screenobject.java //ALIEN
											// SHOT
			for (Bunker bunkerObj : bunkerObjects) {
				if (shotObj.collide(bunkerObj) == true) {
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 0) {
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit1.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 1) {
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit2.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 2) {
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit4.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 3) {
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit3.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 4) {
						bunkerObj.setSize(new Rectangle(-10, -10, 0, 0));
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						repaint();
					}
				}
			}
		}
		// Player vs Bunkers
		for (Shot shotObj : multipleShots) { // check in screenobject.java
												// //PLAYER SHOT
			for (Bunker bunkerObj : bunkerObjects) {
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 0) {
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit1.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 1) {
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit2.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 2) {
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit4.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 3) {
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					bunkerObj.setImage(bunkerhit3.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 4) {
					bunkerObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					repaint();
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
			collideObjects();
			repaint();
			int randRange = 1024 + generator.nextInt(4000);
			if (mysteryShipPic.location.getX() > randRange) {
				mysteryShipPic.setVector(new MyVector(-10, 0));
			}
			mysteryShipPic.move(mysteryShipPic);
			collideObjects();
			repaint();
		}
	}

	// TODO RANDOM ALIEN LISTENER (always makes it through but does not show up
	// on screen)
	private class EnemyShotTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			enemyShotSwitch = true;
			Random rand = new Random();
			Alien shooterAlien = alienObjects.get(rand.nextInt(alienObjects
					.size() - 1));
			Point p = shooterAlien.getLocation();
			Rectangle r = shooterAlien.getSize();
			shot = new Shot(new Point(p.x + r.width / 2 - 5, p.y + 20),
					new Rectangle(r.x, r.y + 10, 10, 15),
					shotPic.getImage());
			enemyShots.add(shot);
			enemyShotTimerPartTwo.start();
		}
	}

	private class EnemyShotTimerListenerPartTwo implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			repaint();
			for (Shot shot : enemyShots) {
				shot.setVector(new MyVector(0, 5));
				shot.move(shot);
				if (shot.location.getY() > 780){
					enemyShots.remove(shot);
				}
				repaint();
			}
		}
	}

	// LASER CANNON SHOT LISTENER
	private class ShotTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			repaint();
			for (int x = 0; x < multipleShots.size(); x++) {
				multipleShots.get(x).setVector(new MyVector(0, -7));
				multipleShots.get(x).move(multipleShots.get(x));
				if (multipleShots.get(x).location.getY() < 10) {
					multipleShots.remove(x);
					repaint();
				}
			}
		}
	}

	// MOVES ALIENS/CONTROL DIRECTION
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//boolean flag = false;
			for (Alien obj : alienObjects) {
				if (obj.location.getX() > 800) {
					//flag = true;
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
						collideObjects();
						innerLoop.move(innerLoop);

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
				repaint();
			}
		}
		if (enemyShotSwitch == true) {
			for (Shot shot : enemyShots) {
				shot.draw(g);
				repaint();
			}
		}
		if (displayLives.getLife() <= 0) {
			lossMessage.draw(g);
			repaint();
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

			repaint();
			break;
		case KeyEvent.VK_RIGHT:
			laserCannon.setVector(new MyVector(5, 0));
			laserCannon.move(laserCannon);

			repaint();
			break;
		case KeyEvent.VK_SPACE: { // FIRE LASER CANNON
			Point p = laserCannon.getLocation();
			Rectangle r = laserCannon.getSize();
			shot = new Shot(new Point(p.x + r.width / 2 - 3, p.y - 20),
					new Rectangle(500, 650, 5, 15), laserCannonShot.getImage());
			shotSwitch = true;
			shotTimer.start();
			multipleShots.add(shot);
			repaint();
		}
		}
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