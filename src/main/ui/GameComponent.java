package ui;

import model.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GameComponent extends JComponent {
    private static final String FIRE = "./resources/Shoot.wav";
    private static final String ASTEROID = "./resources/Explosion_2.wav";
    private static final String SHIP = "./resources/Explosion_1.wav";

    private Set<Timer> timers;
    private Set<Fire> fires;
    protected static ShipImp myShip;
    private AsteroidMap astMap;
    private Rectangle2D healthBar;
    private int score;
    private boolean progress;

    // EFFECTS: Construct game component.
    public GameComponent() {
        myShip = new ShipImp(10, GameFrame.HEIGHT / 2 - 10);
        this.timers = new HashSet<>();
        addTimers();

        this.fires = new HashSet<>();
        this.astMap = new AsteroidMap();
        healthBar = new Rectangle2D.Double(10, 10, myShip.getHealth() * 2, 10);

        this.progress = false;
    }

    // MODIFIES: this
    // EFFECTS: Method for starting the game progress.
    public void start() {
        myShip.setMovementBounds(new Rectangle2D.Double(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT));
        score = 0;

        // Start each timer
        for (Timer t: timers) {
            t.start();
        }

        addKeyListener(new ShipKeyListener());
        progress = true;
    }

    // MODIFIES: this
    // EFFECTS: Update all the fields
    private void update() {
        requestFocusInWindow();
        myShip.move();
        astMap.moveAsteroids();
        moveFires();

        checkShipCollisions();
        checkFireCollisions();

        repaint();

        if (!isHealthy()) {
            gameOver();
        }
    }

    // EFFECTS: Paint every thing on the frame
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintComponent(g2);
    }

    private void paintComponent(Graphics2D g) {
        // Draw health bar
        drawHealthBar(g);

        // Draw ship
        myShip.draw(g);

        // Draw each asteroid
        astMap.draw(g);

        // Draw fires
        for (Fire f: fires) {
            f.draw(g);
        }

        // Draw score
        drawScore(g);

        // Draw game over
        drawGameOver(g);
    }

    // EFFECTS: Draw health bar on the frame.
    private void drawHealthBar(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(10, 11, myShip.getHealth() * 2, 10);
        g.draw(healthBar);
    }

    // EFFECTS: Draw score on the frame.
    private void drawScore(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 20));
        g.drawString(Integer.toString(score), 30, GameFrame.HEIGHT - 20);
    }

    // EFFECTS: Draw the game over on the frame.
    private void drawGameOver(Graphics2D g) {
        if (!isHealthy()) {
            g.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 100));
            g.drawString("Game Over", GameFrame.WIDTH / 5, GameFrame.HEIGHT / 2);
        }
    }

    // MODIFIES: this
    // EFFECTS: Add timers to the set
    private void addTimers() {
        timers.add(new Timer(1000 / 60, (a) -> update()));
        timers.add(new Timer(1000 / 4, (a) -> makeAsteroid()));
        timers.add(new Timer(1000 / 2, (a) -> makeFire()));
    }

    // MODIFIES: this
    // EFFECTS: Make an asteroid and add it to the map.
    private void makeAsteroid() {
        astMap.addAsteroids(Factory.getInstance().makeAsteroid());
    }

    // MODIFIES: this
    // EFFECTS: Make a fire and add it to the fire set.
    private void makeFire() {
        fires.add(new Fire(myShip.getFirePointX(), myShip.getFirePointY()));
        soundEffect(FIRE);
    }

    // MODIFIES: this
    // EFFECTS: Move each fire.
    private void moveFires() {
        for (Iterator<Fire> it = fires.iterator(); it.hasNext();) {
            Fire f = it.next();

            if (!f.isVisible()) {
                it.remove();
            }
            f.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: Check if there is a collision. If true, play a sound effect.
    private void checkShipCollisions() {
        if (astMap.checkAsteroidCollisions(myShip)) {
            soundEffect(SHIP);
        }
    }

    // MODIFIES: this
    // EFFECTS: Check if there is a collision. If true, play a sound effect and add 100 point to score.
    private void checkFireCollisions() {
        if (astMap.checkAsteroidCollisions(fires)) {
            soundEffect(ASTEROID);
            score += 100;
        }
    }

    // EFFECTS: Return true is ship's health is above 0.
    private boolean isHealthy() {
        return myShip.getHealth() > 0;
    }

    // MODIFIES: this, scoreboard
    // EFFECTS: Stop everything and add a score to the scoreboard while game is over.
    private void gameOver() {
        // Stop timers
        for (Timer t: timers) {
            t.stop();
        }
        progress = false;
        removeKeyListener(new ShipKeyListener());

        makeScore();
    }

    // MODIFIES: scoreboard
    // EFFECT: Add the score with user's name to the scoreboard.
    private void makeScore() {
        String name = JOptionPane.showInputDialog(null,
                "Game Over!\n" + "Please enter your name here:");

        GameFrame.addScore(new Score(name, score));
    }

    // EFFECTS: Reset everything.
    public void playAgain() {
        myShip = new ShipImp(10, GameFrame.HEIGHT / 2 - 10);
        healthBar = new Rectangle2D.Double(10, 10, myShip.getHealth() * 2, 10);
        astMap.clear();
        fires.clear();
        start();
    }

    // EFFECTS: Return true if the game is in progress.
    public boolean isInProgress() {
        return progress;
    }

    // EFFECTS: Play a sound.
    public void soundEffect(String fileName) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
