package com.GameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean pressedUP, pressedDOWN, pressedLEFT, pressedRIGHT;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            pressedUP = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            pressedDOWN = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            pressedLEFT = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            pressedRIGHT = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            pressedUP = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            pressedDOWN = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            pressedLEFT = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            pressedRIGHT = false;
        }
    }

}
