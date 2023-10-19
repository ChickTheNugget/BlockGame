package com.Controller;

import com.View.GameRenderer;

import javax.swing.*;

public class BlockGame {

    private GameRenderer gameRenderer;
    private long previousTime;
    private boolean isRunning;

    public BlockGame() {
        gameRenderer = new GameRenderer();
    }

    public void run() {
        System.out.println("2");
        gameRenderer.setupWindow();
        gameRenderer.showMainMenu();

        previousTime = System.currentTimeMillis();
        isRunning = true;

        // while (isRunning)
        // {
        // gameRenderer.TESTSHOWMENU();
        // }

    }

}
