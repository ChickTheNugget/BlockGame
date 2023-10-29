package com.GameLogic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * The Player class is responsible for keeping track of the character's
 * position,
 * it draws the character to the screen and checks collision.
 */
public class Player {

    GamePanel gamePanel;

    private int xPosition;
    private int yPosition;
    private int speed;
    private BufferedImage playerImage;

    /**
     * The constructor of a Player class.
     * 
     * @param gamePanel the GamePanel to which the Player belongs
     */
    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        xPosition = 0;
        yPosition = 0;
        speed = 4;
        try {
            playerImage = ImageIO.read(new File("src/com/assets/character.png"));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * Resets the players position to tbe beginnning of a map.
     */
    public void respawn() {
        setXPosition(0, 0);
        setYPosition(0, 0);
    }

    /**
     * Sets the players x postition to a new x plus some step.
     * 
     * @param x    the new x position
     * @param plus the step to be added
     */
    public void setXPosition(int x, int plus) {
        if (!checkBounds(x + plus, yPosition)) {

            return;
        }
        int collisionCheck;
        if (plus < 0) {
            collisionCheck = handleCorrision(x + plus, yPosition, "left");
        } else {
            collisionCheck = handleCorrision(x + plus, yPosition, "right");
        }
        if (collisionCheck == 0) {
            xPosition = x + plus;
        }

        return;

    }

    /**
     * Sets the players y postition to a new y plus some step.
     * 
     * @param y    the new y position
     * @param plus the step to be added
     */
    public void setYPosition(int y, int plus) {
        if (!checkBounds(xPosition, y + plus)) {
            return;
        }
        int collisionCheck;
        if (plus < 0) {
            collisionCheck = handleCorrision(xPosition, y + plus, "up");
        } else {
            collisionCheck = handleCorrision(xPosition, y + plus, "down");
        }
        if (collisionCheck == 0) {
            yPosition = y + plus;
        }
        return;
    }

    public int getPlayerX() {
        return xPosition;
    }

    public int getPlayerY() {
        return yPosition;
    }

    public int getPlayerSpeed() {
        return speed;
    }

    /**
     * Checks wheter the player has reached the finish field.
     * 
     * @return true if he is at the end of a maze, false otherwise
     */
    public boolean atEnd() {
        if (xPosition > gamePanel.tileSize * (gamePanel.numColumns - 1)
                && yPosition > gamePanel.tileSize * (gamePanel.numRows - 1)) {

            return true;
        }
        return false;

    }

    /**
     * Draws the player.
     * 
     * @param g the Graphics2D hands, necessary to draw
     */
    public void drawPlayer(Graphics2D g) {
        g.drawImage(playerImage, xPosition,
                yPosition,
                gamePanel.tileSize / 2 + 2, gamePanel.tileSize / 2 + 2, null);
    }

    /**
     * Checks whether a given position is inside the game's bounds.
     * 
     * @param xPos the to be checked x position
     * @param yPos the to be checked y position
     * @return true if it is inside the game's bounds, false otherwise
     */
    public boolean checkBounds(int xPos, int yPos) {
        if (xPos < 0 || yPos < 0 || xPos + gamePanel.tileSize / 2 > gamePanel.screenWidth
                || yPos + gamePanel.tileSize / 2 > gamePanel.screenHeight) {
            return false;
        }
        return true;
    }

    /**
     * Checks and handles whether a player collides with a wall of movable wall.
     * 
     * @param xPos      the xPosition of the player
     * @param yPos      the yPosition of the player
     * @param direction the direction the player is moving in
     * @return returns 0 if there is no collision with a wall of movable wall
     *         returns 1 if he collides with a wall
     *         and returns 3 if he collides with a movable wall
     */
    public int handleCorrision(int xPos, int yPos, String direction) {
        for (int wall = 0; wall < 20 * 20; wall++) {
            int wallX = gamePanel.blocks[wall].xPosition;
            int wallY = gamePanel.blocks[wall].yPosition;
            int tileSize = gamePanel.tileSize;
            // wall
            if (rectangleOverlap(wallX, wallY, tileSize, tileSize,
                    xPos, yPos, tileSize / 2, tileSize / 2)
                    && gamePanel.blocks[wall].blockType == 1) {
                return 1;

            }

        }

        for (Block block : gamePanel.walls.movableWalls) {
            int wallX = block.xPosition;
            int wallY = block.yPosition;
            if (rectangleOverlap(wallX, wallY, gamePanel.tileSize, gamePanel.tileSize,
                    xPos, yPos, gamePanel.tileSize / 2, gamePanel.tileSize / 2)) {
                handleMovableWallCollision(block, direction);
                return 3;
            }
        }
        return 0;
    }

    /**
     * Decides what happens if the player collides with movable wall.
     * 
     * @param block     the movable wall
     * @param direction the direction in which the player moved
     */
    public void handleMovableWallCollision(Block block, String direction) {
        int wallX = block.xPosition;
        int wallY = block.yPosition;
        if (direction.equals("up")
                && isMovable(wallX + 1, wallY - speed, block)
                && isMovable(wallX + gamePanel.tileSize - 1, wallY - speed, block)
                && checkBounds(wallX + 1, wallY - speed)
                && checkBounds(wallX + gamePanel.tileSize - 1, wallY - speed)) {
            block.yPosition -= speed;
            gamePanel.playClip(0);
        }
        if (direction.equals("down")
                && isMovable(wallX + 1, wallY + speed + gamePanel.tileSize, block)
                && isMovable(wallX + gamePanel.tileSize - 1,
                        wallY + speed + gamePanel.tileSize, block)
                && checkBounds(wallX + 1, wallY + speed + gamePanel.tileSize)
                && checkBounds(wallX + gamePanel.tileSize - 1,
                        wallY + speed + gamePanel.tileSize)) {
            block.yPosition += speed;
            gamePanel.playClip(0);

        }
        if (direction.equals("left")
                && isMovable(wallX - speed, wallY + 1, block)
                && isMovable(wallX - speed, wallY + gamePanel.tileSize - 1, block)
                && checkBounds(wallX - speed, wallY + 1)
                && checkBounds(wallX - speed, wallY + gamePanel.tileSize - 1)) {
            block.xPosition -= speed;
            gamePanel.playClip(0);
        }

        if (direction.equals("right")
                && isMovable(wallX + speed + gamePanel.tileSize, wallY + 1, block)
                && isMovable(wallX + speed + gamePanel.tileSize,
                        wallY + gamePanel.tileSize - 1, block)
                && checkBounds(wallX + speed + gamePanel.tileSize, wallY + 1)
                && checkBounds(wallX + speed + gamePanel.tileSize,
                        wallY + gamePanel.tileSize - 1)) {
            block.xPosition += speed;
            gamePanel.playClip(0);
        }
    }

    /**
     * Checks for a given movable block whether (x, y) contains a wall or movable.
     * 
     * @param x     the to be checked x position
     * @param y     the to be checked y position
     * @param block the movable wall we want to move
     * @return true if we can move the wall, false otherwise
     */
    public boolean isMovable(int x, int y, Block block) {
        if (x > gamePanel.screenWidth || x < 0 || y > gamePanel.screenHeight || y < 0) {
            return true;
        }
        for (Block current : gamePanel.blocks) {
            if (current.xPosition <= x && x <= current.xPosition + gamePanel.tileSize
                    && current.yPosition <= y && y <= current.yPosition + gamePanel.tileSize) {
                if (current.blockType == 1) {
                    return false;
                }
            }

        }
        for (Block current : gamePanel.walls.movableWalls) {
            if (current.xPosition <= x && x <= current.xPosition + gamePanel.tileSize
                    && current.yPosition <= y && y <= current.yPosition + gamePanel.tileSize) {
                return false;
            }

        }
        return true;
    }

    /**
     * Checks whether two rectangles overlap.
     * 
     * @param x1 xPostition of the first rectangle
     * @param y1 yPostition of the first rectangle
     * @param w1 width of the first rectangle
     * @param h1 height of the first rectangle
     * @param x2 xPostition of the second rectangle
     * @param y2 yPostition of the second rectangle
     * @param w2 width of the second rectangle
     * @param h2 height of the second rectangle
     * @return returns true if they overlap, false otherwise
     */
    public boolean rectangleOverlap(int x1, int y1, int w1, int h1,
            int x2, int y2, int w2, int h2) {
        int topLeftX1 = x1;
        int topLeftY1 = y1;
        int bottomRightX1 = x1 + w1;
        int bottomRightY1 = y1 + h1;
        int topLeftX2 = x2;
        int topLeftY2 = y2;
        int bottomRightX2 = x2 + w2;
        int bottomRightY2 = y2 + h2;

        if (topLeftX1 >= bottomRightX2 || topLeftX2 >= bottomRightX1) {
            return false;
        }
        if (topLeftY1 >= bottomRightY2 || topLeftY2 >= bottomRightY1) {
            return false;
        }
        return true;
    }

}
