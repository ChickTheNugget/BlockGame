package com.GameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles key presses.
 */
public class KeyHandler implements KeyListener {

    public boolean pressedUp;
    public boolean pressedDown;
    public boolean pressedLeft;
    public boolean pressedRight;

    // Not needed, hence empty
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            pressedUp = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            pressedDown = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            pressedLeft = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            pressedRight = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            pressedUp = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            pressedDown = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            pressedLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            pressedRight = false;
        }
    }

}
