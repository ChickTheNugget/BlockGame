package com.GameLogic;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Class storing the wall kinds, blocks and movable walls of a level.
 */
public class Walls {
    GamePanel gamePanel;
    Wall[] walls;
    Block[] blocks;
    ArrayList<Block> movableWalls;

    /**
     * Constructor for the Walls Class.
     * 
     * @param gamePanel the game for which it stores the wall information.
     */
    public Walls(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        walls = new Wall[4];
        getImage();
        blocks = new Block[20 * 20];
        movableWalls = new ArrayList<>();

    }

    /**
     * Gets the images from their respective files and fills the walls array.
     */
    public void getImage() {
        try {
            walls[0] = new Wall();
            walls[0].wallImage = ImageIO.read(new File("src/com/assets/wall.png"));
            walls[1] = new Wall();
            walls[1].wallImage = ImageIO.read(new File("src/com/assets/floor.png"));
            walls[2] = new Wall();
            walls[2].wallImage = ImageIO
                    .read(new File("src/com/assets/movable_wall.png"));
            walls[3] = new Wall();
            walls[3].wallImage = ImageIO
                    .read(new File("src/com/assets/win_field.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills the blocks array with the data from the level file.
     */
    public void getWall() {
        try {
            File wall = new File(gamePanel.levelPath);
            if (!wall.exists()) {
                System.err.println("Map not found");
                System.exit(1);
            }

            Scanner scanner = new Scanner(wall);
            int wallCounter = 0;
            for (int row = 0; row < 20; row++) {
                String wallStrip = scanner.nextLine();
                char[] charArray = wallStrip.toCharArray();
                for (int column = 0; column < 20; column++) {
                    gamePanel.blocks[wallCounter] = new Block();
                    gamePanel.blocks[wallCounter].xPosition = column * gamePanel.tileSize;
                    gamePanel.blocks[wallCounter].yPosition = row * gamePanel.tileSize;
                    if (charArray[column] == '#') {
                        gamePanel.blocks[wallCounter].blockType = 1;

                    } else if (charArray[column] == '.') {
                        gamePanel.blocks[wallCounter].blockType = 2;
                    } else if (charArray[column] == 'M') {
                        gamePanel.blocks[wallCounter].blockType = 2;
                        Block newWall = new Block();
                        newWall.xPosition = gamePanel.blocks[wallCounter].xPosition;
                        newWall.yPosition = gamePanel.blocks[wallCounter].yPosition;
                        movableWalls.add(newWall);
                    }
                    wallCounter += 1;

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error while retrieving files: " + e);
            e.printStackTrace();
            System.exit(e.hashCode());
        }
    }

    /**
     * Draws a floor tile.
     * 
     * @param g2 handle to a Graphics2D instance to draw the floor.
     */
    public void drawFloor(Graphics2D g2) {
        for (int row = 0; row < 20; row++) {
            for (int column = 0; column < 20; column++) {
                g2.drawImage(walls[1].wallImage, column * gamePanel.tileSize,
                        row * gamePanel.tileSize,
                        gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
    }

    /**
     * Draws a wall tile.
     * 
     * @param g2 handle to a Graphics2D instance to draw the wall.
     */
    public void drawWall(Graphics2D g2) {
        for (int wall = 0; wall < 20 * 20; wall++) {
            if (gamePanel.blocks[wall].blockType == 1) {
                g2.drawImage(walls[0].wallImage, gamePanel.blocks[wall].xPosition,
                        gamePanel.blocks[wall].yPosition,
                        gamePanel.tileSize, gamePanel.tileSize, null);
            }
            if (wall == 20 * 20 - 1) {
                g2.drawImage(walls[3].wallImage, gamePanel.blocks[wall].xPosition,
                        gamePanel.blocks[wall].yPosition,
                        gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
        for (Block block : movableWalls) {
            g2.drawImage(walls[2].wallImage, block.xPosition,
                    block.yPosition,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

}
