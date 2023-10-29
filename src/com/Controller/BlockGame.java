package com.Controller;

import com.GameRenderer;

/**
 * Main Game class, it handes creating a new instance of our GameRenderer
 * and the setup for it to function.
 */
public class BlockGame {

    private GameRenderer gameRenderer;

    public BlockGame() {
        gameRenderer = new GameRenderer();
    }

    public void run() {
        gameRenderer.setupWindow();
        gameRenderer.showMainMenu();
    }

}
