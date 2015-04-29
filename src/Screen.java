import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements KeyListener {
	// RANDOM ALIEN SHOT LISTENER
	private class EnemyShotTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			enemyShotSwitch = true;
			Random rand = new Random();
			Alien shooterAlien = alienObjects.get(rand.nextInt(alienObjects
					.size() - 1));
			boolean keepLooping = true;
			while (shooterAlien.location.getX() < -10 && keepLooping == true) {
				// doing this results in rand looping forever once all of the
				// aliens are off the screen
				// so let's add yet another ------- safeguard.
				keepLooping = false;
				for (Alien shooter : alienObjects) {
					if (shooter.location.getX() > 10) {
						keepLooping = true;
					}
				}
				// because hey, why write good code when you can add more
				// garbage?
				shooterAlien = alienObjects.get(rand.nextInt(alienObjects
						.size() - 1));
			}
			Point p = shooterAlien.getLocation();
			Rectangle r = shooterAlien.getSize();
			shot = new Shot(new Point(p.x + r.width / 2 - 5, p.y + 20),
					new Rectangle(r.x, r.y + 10, 10, 15), shotPic.getImage());
			enemyShots.add(shot);
			if (pew != null) {
				pew.play();
			}
			enemyShotTimerPartTwo.start();
		}
	}

	// Alien shot timer listener
	private class EnemyShotTimerListenerPartTwo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			repaint();
			for (int x = 0; x < enemyShots.size(); x++) {
				enemyShots.get(x).setVector(new MyVector(0, 7));
				enemyShots.get(x).move(enemyShots.get(x));
				collideObjects();
				if (enemyShots.get(x).location.getY() > 780) {
					enemyShots.remove(x);
				}
			}
			repaint();
		}
	}

	// MYSTER SHIP TIMER (BACK AND FORTH)
	private class MysterymovListener implements ActionListener {
		@Override
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

	// LASER CANNON SHOT LISTENER
	private class ShotTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			repaint();
			for (int x = 0; x < playerShots.size(); x++) {
				playerShots.get(x).setVector(new MyVector(0, -7));
				playerShots.get(x).move(playerShots.get(x));
				if (playerShots.get(x).location.getY() < 10) {
					playerShots.remove(x);
					repaint();
				}
			}
			repaint();
		}
	}

	// MYSTERY SHIP TIMER (GETS GOING LEFT)
	private class startMysListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Timer mysterymovTimer = new Timer(100, new MysterymovListener());
			mysteryShipPic.setVector(new MyVector(-10, 0));
			turnaround = generator.nextInt(2500);
			turnaround = 0 - turnaround;
			mysterymovTimer.start();
			startMystery.stop();
		}
	}

	// MOVES ALIENS/CONTROL DIRECTION
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for (Alien obj : alienObjects) {
				if (obj.location.getX() > 800) {
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

				if (obj.location.getX() < 200 && obj.location.getX() > -15) {
					/*
					 * So we add an extra condition here... But this results in
					 * the aliens that are left on the screen shooting less
					 * Because the ones off of the screen are being triggered
					 * for shots, too. So then we add a condition for shot
					 * selection as well. SEE LINE 238
					 */

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

	public static int screenWidth = 1024;
	public static int screenHeight = 768;
	public static Score displayScore = new Score(new Point(750, 25),
			new Rectangle(10, 10, 10, 10), 0);
	public static Lives displayLives = new Lives(new Point(175, 25),
			new Rectangle(10, 10, 10, 10), 3);
	public static ImageIcon enemyType1Frame1 = new ImageIcon(
			Messages.getString("Screen.0")); //$NON-NLS-1$
	public static ImageIcon enemyType1Frame2 = new ImageIcon(
			Messages.getString("Screen.1")); //$NON-NLS-1$
	public static ImageIcon enemyType2Frame1 = new ImageIcon(
			Messages.getString("Screen.2")); //$NON-NLS-1$
	public static ImageIcon enemyType2Frame2 = new ImageIcon(
			Messages.getString("Screen.3")); //$NON-NLS-1$
	public static ImageIcon enemyType3Frame1 = new ImageIcon(
			Messages.getString("Screen.4")); //$NON-NLS-1$
	public static ImageIcon enemyType3Frame2 = new ImageIcon(
			Messages.getString("Screen.5")); //$NON-NLS-1$
	public static ImageIcon redSaucer = new ImageIcon(Messages.getString("Screen.6")); //$NON-NLS-1$
	public static ImageIcon laserCanon = new ImageIcon(Messages.getString("Screen.7")); //$NON-NLS-1$
	public static ImageIcon bunker = new ImageIcon(Messages.getString("Screen.8")); //$NON-NLS-1$
	public static ImageIcon shotPic = new ImageIcon(Messages.getString("Screen.9")); //$NON-NLS-1$
	public static ImageIcon laserCannonShot = new ImageIcon(
			Messages.getString("Screen.10")); //$NON-NLS-1$
	public static ImageIcon bunkerhit1 = new ImageIcon(Messages.getString("Screen.11")); //$NON-NLS-1$
	public static ImageIcon bunkerhit2 = new ImageIcon(Messages.getString("Screen.12")); //$NON-NLS-1$
	public static ImageIcon bunkerhit3 = new ImageIcon(Messages.getString("Screen.13")); //$NON-NLS-1$
	public static ImageIcon bunkerhit4 = new ImageIcon(Messages.getString("Screen.14")); //$NON-NLS-1$
	private static boolean shotSwitch = false;
	private static boolean enemyShotSwitch = false;
	private static ImageIcon explodingPlayerShip = new ImageIcon(
			Messages.getString("Screen.15")); //$NON-NLS-1$
	private static Message lossMessage = new Message(new Point(300, 300),
			new Rectangle(300, 300, 600, 600));
	private static AudioClip pew = null;
	private static AudioClip bg = null;
	private static AudioClip genericShipExplosion = null;
	private static AudioClip bunkerHit = null;
	private ArrayList<Alien> alienObjects;
	private ArrayList<Bunker> bunkerObjects;
	private ArrayList<Shot> playerShots;
	private ArrayList<Shot> enemyShots;
	private javax.swing.Timer timer;
	private javax.swing.Timer shotTimer;
	private javax.swing.Timer enemyShotTimer;
	private javax.swing.Timer startMystery;

	private javax.swing.Timer enemyShotTimerPartTwo;

	private LaserCannon laserCannon;

	private Shot shot;

	private MysteryShip mysteryShipPic;

	private Random generator;

	private int turnaround;

	public Screen() {
		try {
			pew = Applet
					.newAudioClip(new URL(
							Messages.getString("Screen.16"))); //$NON-NLS-1$
			bg = Applet
					.newAudioClip(new URL(
							Messages.getString("Screen.17"))); //$NON-NLS-1$
			genericShipExplosion = Applet
					.newAudioClip(new URL(
							Messages.getString("Screen.18"))); //$NON-NLS-1$
			bunkerHit = Applet
					.newAudioClip(new URL(
							Messages.getString("Screen.19"))); //$NON-NLS-1$
			bg.loop();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.black);
		lossMessage.setText(Messages.getString("Screen.20")); //$NON-NLS-1$
		alienObjects = new ArrayList<Alien>();
		bunkerObjects = new ArrayList<Bunker>();
		playerShots = new ArrayList<Shot>();
		enemyShots = new ArrayList<Shot>();

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

	// This method could really use a cleanup.
	// Extract some of the behavior, maybe put it is the object classes?
	// There are a lot of magic numbers and tons of repetition.
	public void collideObjects() {
		// Aliens hitting player
		for (Shot shots : enemyShots) {
			if (shots.collide(laserCannon) == true) {
				shots.setLocation(new Point(-10, -10));
				laserCannon.setImage(explodingPlayerShip.getImage());
				paintImmediately(getVisibleRect());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (displayLives.getLife() > 0) {
					displayLives.setLife(displayLives.getLife() - 1);

					laserCannon.setImage(laserCanon.getImage());
					laserCannon.setLocation(new Point(100, 650));
					if (displayLives.getLife() <= 0) {
						laserCannon.setLocation(new Point(-300, -300));
					}
				}
				break;
			}
		}

		// Player vs Mystery Ship
		for (Shot shots : playerShots) {
			if (shots.collide(mysteryShipPic) == true) {
				mysteryShipPic.setSize(new Rectangle(0, 0, 0, 0));
				mysteryShipPic.setLocation(new Point(-300, -300));
				displayScore.setScore(displayScore.getScore() + 100);
				repaint();
			}
		}

		// Alien Ship body vs Bunker
		for (Alien alienShip : alienObjects) {
			for (Bunker bunkerObj : bunkerObjects) {
				if (alienShip.collide(bunkerObj) == true) {
					if (bunkerObj.getHits() == 0){
					bunkerObj.setImage(bunkerhit1.getImage());
					}
					else if (bunkerObj.getHits() == 1) {
					bunkerObj.setImage(bunkerhit2.getImage());
						}
					else if (bunkerObj.getHits() == 2) {
					bunkerObj.setImage(bunkerhit3.getImage());	
					}
					else if (bunkerObj.getHits() == 3) {
					bunkerObj.setImage(bunkerhit4.getImage());
					}
					else if (bunkerObj.getHits() == 4) {
					bunkerObj.setImage(bunkerhit4.getImage());
					}
					bunkerObj.setHits(bunkerObj.getHits() + 1);
					repaint();
				}
			}
		}

		// Alien Ship bodies vs player ship body
		for (Alien alienShip : alienObjects) {
			if (alienShip.collide(laserCannon)) {
				laserCannon.setImage(explodingPlayerShip.getImage());
				paintImmediately(getVisibleRect());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (displayLives.getLife() > 0) {
					displayLives.setLife(displayLives.getLife() - 1);

					laserCannon.setImage(laserCanon.getImage());
					laserCannon.setLocation(new Point(100, 650));
					if (displayLives.getLife() <= 0) {
						laserCannon.setLocation(new Point(-300, -300));
					}
				}
				break;
			}
		}

		// Alien vs Player Shots
		for (Shot shotObj : playerShots) {
			for (Alien alienObj : alienObjects) {
				if (shotObj.collide(alienObj) == true) {
					if (genericShipExplosion != null) {
						genericShipExplosion.play();
					}
					alienObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setLocation(new Point(-10, -10));
					alienObj.setLocation(new Point(-1000, -10));
					/*
					 * What is happening is that when we throw the aliens off of
					 * the screen they get caught up by our movement
					 * initialization method. SEE LINE ---450--- 530
					 */
					displayScore.setScore(displayScore.getScore() + 10);
					repaint();

				}
			}
		}
		// Alien shots vs Bunkers
		for (Shot shotObj : enemyShots) {
			for (Bunker bunkerObj : bunkerObjects) {
				if (shotObj.collide(bunkerObj) == true) {
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 0) {
						if (bunkerHit != null) {
							bunkerHit.play();
						}
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit1.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 1) {
						if (bunkerHit != null) {
							bunkerHit.play();
						}
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit2.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 2) {
						if (bunkerHit != null) {
							bunkerHit.play();
						}
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit4.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 3) {
						if (bunkerHit != null) {
							bunkerHit.play();
						}
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						bunkerObj.setImage(bunkerhit3.getImage());
						repaint();
						bunkerObj.setHits(bunkerObj.getHits() + 1);
					}
					if (shotObj.collide(bunkerObj) == true
							&& bunkerObj.getHits() == 4) {
						if (bunkerHit != null) {
							bunkerHit.play();
						}
						bunkerObj.setSize(new Rectangle(-10, -10, 0, 0));
						shotObj.setSize(new Rectangle(-10, -10, 0, 0));
						repaint();
					}
				}
			}
		}
		// Player shots vs Bunkers
		for (Shot shotObj : playerShots) { // check in screenobject.java
											// //PLAYER SHOT
			for (Bunker bunkerObj : bunkerObjects) {
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 0) {
					if (bunkerHit != null) {
						bunkerHit.play();
					}
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setLocation(new Point(-10, -10));
					bunkerObj.setImage(bunkerhit1.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 1) {
					if (bunkerHit != null) {
						bunkerHit.play();
					}
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setLocation(new Point(-10, -10));
					bunkerObj.setImage(bunkerhit2.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 2) {
					if (bunkerHit != null) {
						bunkerHit.play();
					}
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setLocation(new Point(-10, -10));
					bunkerObj.setImage(bunkerhit4.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 3) {
					if (bunkerHit != null) {
						bunkerHit.play();
					}
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setLocation(new Point(-10, -10));
					bunkerObj.setImage(bunkerhit3.getImage());
					repaint();
					bunkerObj.setHits(bunkerObj.getHits() + 1);
				}
				if (shotObj.collide(bunkerObj) == true
						&& bunkerObj.getHits() == 4) {
					if (bunkerHit != null) {
						bunkerHit.play();
					}
					bunkerObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setSize(new Rectangle(-10, -10, 0, 0));
					shotObj.setLocation(new Point(-10, -10));
					repaint();
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
			if (laserCannon.location.getX() >= 5) {
				laserCannon.setVector(new MyVector(-5, 0));
				laserCannon.move(laserCannon);
				repaint();
			}
			break;

		case KeyEvent.VK_RIGHT:
			if (laserCannon.location.getX() <= 970) {
				laserCannon.setVector(new MyVector(5, 0));
				laserCannon.move(laserCannon);
				repaint();
			}
			break;
		case KeyEvent.VK_SPACE: { // FIRE LASER CANNON
			Point p = laserCannon.getLocation();
			Rectangle r = laserCannon.getSize(); // This should not be called
			// every time that the cannon is fired.
			// It should be called once at most and then stored, as the size
			// of the cannon does not change.
			// There are many many many instances like this through this
			// project.
			shot = new Shot(new Point(p.x + r.width / 2 - 3, p.y - 20),
					new Rectangle(500, 650, 5, 15), laserCannonShot.getImage());
			if (pew != null) {
				pew.play();
			}
			shotSwitch = true;
			shotTimer.start();
			playerShots.add(shot);
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

	// DRAW SCORE, LIVES, LASER CANNON, MYSTERYSHIP, SHOTS
	@Override
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
			for (Shot shot : playerShots) {
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

}