package com.GameLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Represents an instance of a Level, responsible for rendering the game and
 * updating the
 * game state.
 */
public class GamePanel extends JPanel implements Runnable {
    final int tileSize = 40;
    final int numColumns = 20;
    final int numRows = 20;
    final int screenWidth = tileSize * numColumns;
    final int screenHeight = tileSize * numRows;
    final int fps = 60;

    Frame parentFrame;
    String levelPath;

    boolean notWon = true;

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;

    Player player = new Player(this);
    Walls walls = new Walls(this);
    Block[] blocks;

    Sound sound = new Sound();

    /**
     * Constructor for The GamePanel Class.
     * 
     * @param level the path to the file containing our level
     * @param frame the frame the game will be rendered to
     */
    public GamePanel(String level, Frame frame) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        blocks = new Block[20 * 20];
        levelPath = level;
        walls.getWall();

        parentFrame = frame;
    }

    /**
     * Starts a new thread to run our Game.
     */
    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000 / fps;
        double nextInterval = System.nanoTime() + interval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remaining = nextInterval - System.nanoTime();
                remaining = remaining / 1000000;
                if (remaining < 0) {
                    remaining = 0;
                }
                Thread.sleep((long) remaining);
                nextInterval += interval;

            } catch (InterruptedException e) {
                System.err.println("Error while trying to sleep: " + e);
                e.printStackTrace();
                System.exit(e.hashCode());
            }

        }

    }

    /**
     * Runs all the necessary details on each Frame to update the game state.
     */
    public void update() {
        if (keyHandler.pressedUp) {
            player.setYPosition(player.getPlayerY(), -player.getPlayerSpeed());
        }
        if (keyHandler.pressedDown) {
            player.setYPosition(player.getPlayerY(), player.getPlayerSpeed());
        }
        if (keyHandler.pressedLeft) {
            player.setXPosition(player.getPlayerX(), -player.getPlayerSpeed());
        }
        if (keyHandler.pressedRight) {
            player.setXPosition(player.getPlayerX(), player.getPlayerSpeed());
        }
        if (player.atEnd() && notWon) {
            notWon = false;
            // play sound pls
            playClip(2);
            // clip kinda weird
            parentFrame.dispose();
        }
    }

    /**
     * Paints our GamePanel.
     * 
     * @param g a Handle to Graphics to draw our GamePanel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        walls.drawFloor(g2);
        walls.drawWall(g2);
        player.drawPlayer(g2);
        g2.dispose();
    }

    /**
     * Starts playing a sound on repeat.
     * 
     * @param soundIndex the index of the to be played sound
     *                   in the list of sounds of our Sound instance
     */
    public void playMusic(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
        sound.loop();
    }

    /**
     * Stops a currently palying sound.
     */
    public void stopMusic() {
        sound.stop();
    }

    /**
     * Plays a sound from its Sound instance once.
     * 
     * @param soundIndex the index of the to be played sound
     *                   in the list of sounds of our Sound instance
     */
    public void playClip(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
    }

}
