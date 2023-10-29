package com.GameLogic;

import java.awt.image.BufferedImage;

/**
 * Represents the differen kinds of blocks.
 * Stores the image of the block type which will be used to draw said type.
 */
public class Wall {
    public BufferedImage wallImage;
    public boolean collision = false;
    public boolean movable = false;

}
