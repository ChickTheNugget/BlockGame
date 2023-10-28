package com.GameLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int tileSize = 40;
    final int numColumns = 20;
    final int numRows = 20;
    final int screenWidth = tileSize * numColumns;
    final int screenHeight = tileSize * numRows;
    final int fps = 60;

    Frame parentFrame;
    String LevelPath;

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread;

    Player player = new Player(this);
    Walls walls = new Walls(this);
    Block[] blocks;

    Sound sound = new Sound();

    public GamePanel(String level, Frame frame) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        blocks = new Block[20 * 20];
        LevelPath = level;
        walls.getWall();

        parentFrame = frame;
    }

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

            } catch (Exception e) {
                // TODO: handle exception

            }

        }

    }

    public void update() {
        if (keyHandler.pressedUP) {
            player.setYPosition(player.getPlayerY(), -player.getPlayerSpeed());
        }
        if (keyHandler.pressedDOWN) {
            player.setYPosition(player.getPlayerY(), player.getPlayerSpeed());
        }
        if (keyHandler.pressedLEFT) {
            player.setXPosition(player.getPlayerX(), -player.getPlayerSpeed());
        }
        if (keyHandler.pressedRIGHT) {
            player.setXPosition(player.getPlayerX(), player.getPlayerSpeed());
        }
        if (player.atEnd()) {
            // play sound pls
            parentFrame.dispose();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        walls.drawFloor(g2);
        walls.drawWall(g2);
        player.drawPlayer(g2);
        g2.dispose();
    }

    public void playMusic(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playClip(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
    }

}
