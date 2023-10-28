package com.GameLogic;

import java.awt.Color;
import java.awt.Graphics2D;

import com.UI.Menu;

public class Player {

    GamePanel gamePanel;
    

    private int xPosition;
    private int yPosition;
    private int speed;

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        xPosition = 0;
        yPosition = 0;
        speed = 4;
    }

    public void respawn() {
        setXPosition(0, 0);
        setYPosition(0, 0);
    }

    public void setXPosition(int x, int plus) {
        if (!checkBounds(x + plus, yPosition)) {

            return;
        }
        int collisionCheck;
        if (plus < 0) {
            collisionCheck = checkCollision(x + plus, yPosition, "left");
        } else {
            collisionCheck = checkCollision(x + plus, yPosition, "right");
        }
        if (collisionCheck == 0) {
            xPosition = x + plus;
        }

        return;

    }

    public void setYPosition(int y, int plus) {
        if (!checkBounds(xPosition, y + plus)) {
            return;
        }
        int collisionCheck;
        if (plus < 0) {
            collisionCheck = checkCollision(xPosition, y + plus, "up");
        } else {
            collisionCheck = checkCollision(xPosition, y + plus, "down");
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

    public void drawPlayer(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(xPosition, yPosition, gamePanel.tileSize / 2 + 2, gamePanel.tileSize / 2 + 2);
    }

    public boolean checkBounds(int xPos, int yPos) {
        if (xPos < 0 || yPos < 0 || xPos + gamePanel.tileSize / 2 > gamePanel.screenWidth
                || yPos + gamePanel.tileSize / 2 > gamePanel.screenHeight) {
            return false;
        }
        return true;
    }

    public int checkCollision(int xPos, int yPos, String direction) {
        for (int wall = 0; wall < 20 * 20; wall++) {
            int wallX = gamePanel.blocks[wall].xPosition;
            int wallY = gamePanel.blocks[wall].yPosition;
            int tileSize = gamePanel.tileSize;
            // wall
            if (squareOverlap(wallX, wallY, tileSize, tileSize, xPos, yPos, tileSize / 2,
                    tileSize / 2)) {

                // wall
                if (gamePanel.blocks[wall].blockType == 1) {
                    return 1;
                }

            }

        }

        for (Block block : gamePanel.walls.movableWalls) {
            int wallX = block.xPosition;
            int wallY = block.yPosition;
            if (squareOverlap(wallX, wallY, gamePanel.tileSize, gamePanel.tileSize,
                    xPos, yPos, gamePanel.tileSize / 2, gamePanel.tileSize / 2)) {
                if (direction.equals("up")) {
                    if (isMovable(wallX + 1, wallY - speed, block)
                            && isMovable(wallX + gamePanel.tileSize - 1, wallY - speed, block)
                            && checkBounds(wallX + 1, wallY - speed)
                            && checkBounds(wallX + gamePanel.tileSize - 1, wallY - speed)) {
                        block.yPosition -= speed;
                        gamePanel.playClip(0);
                    }
                }
                if (direction.equals("down")) {
                    if ((isMovable(wallX + 1, wallY + speed + gamePanel.tileSize, block)
                            && isMovable(wallX + gamePanel.tileSize - 1, wallY + speed + gamePanel.tileSize, block))
                            && checkBounds(wallX + 1, wallY + speed + gamePanel.tileSize)
                            && checkBounds(wallX + gamePanel.tileSize - 1, wallY + speed + gamePanel.tileSize)) {
                        block.yPosition += speed;
                        gamePanel.playClip(0);
                    }
                }
                if (direction.equals("left")) {
                    if (isMovable(wallX - speed, wallY + 1, block)
                            && isMovable(wallX - speed, wallY + gamePanel.tileSize - 1, block)
                            && checkBounds(wallX - speed, wallY + 1)
                            && checkBounds(wallX - speed, wallY + gamePanel.tileSize - 1)) {
                        block.xPosition -= speed;
                        gamePanel.playClip(0);
                    }
                }
                if (direction.equals("right")) {
                    if (isMovable(wallX + speed + gamePanel.tileSize, wallY + 1, block)
                            && isMovable(wallX + speed + gamePanel.tileSize, wallY + gamePanel.tileSize - 1, block)
                            && checkBounds(wallX + speed + gamePanel.tileSize, wallY + 1)
                            && checkBounds(wallX + speed + gamePanel.tileSize, wallY + gamePanel.tileSize - 1)) {
                        block.xPosition += speed;
                        gamePanel.playClip(0);
                    }
                }
                return 3;
            }
        }
        return 0;
    }

    public boolean isMovable(int x, int y, Block block) {
        if (x > gamePanel.screenWidth || x < 0 || y > gamePanel.screenHeight || y < 0) {
            return true;
        }
        for (Block current : gamePanel.blocks) {
            if (current.xPosition <= x && x <= current.xPosition + gamePanel.tileSize && current.yPosition <= y
                    && y <= current.yPosition + gamePanel.tileSize) {
                if (current.blockType == 1) {
                    return false;
                }
            }

        }
        for (Block current : gamePanel.walls.movableWalls) {
            if (current.xPosition <= x && x <= current.xPosition + gamePanel.tileSize && current.yPosition <= y
                    && y <= current.yPosition + gamePanel.tileSize) {
                return false;
            }

        }
        return true;
    }

    public boolean squareOverlap(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
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
