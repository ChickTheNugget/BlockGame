package com.Controller;

import com.GameRenderer;

import javax.swing.*;

public class BlockGame {

    private GameRenderer gameRenderer;
    private long previousTime;
    private boolean isRunning;

    public BlockGame() {
        gameRenderer = new GameRenderer();
    }

    public void run() {
        gameRenderer.setupWindow();
        gameRenderer.showMainMenu();

        previousTime = System.currentTimeMillis();
        isRunning = true;

    }

}
