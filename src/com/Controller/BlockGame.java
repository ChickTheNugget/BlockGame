package com.Controller;

import com.View.GameRenderer;

import javax.swing.*;


public class BlockGame {

    private GameRenderer gameRenderer;
    private long previousTime;
    private boolean isRunning;

    public BlockGame()
    {
        gameRenderer = new GameRenderer();
    }

    public void run()
    {
        gameRenderer.showWindow();
        previousTime = System.currentTimeMillis();
        isRunning = false;

        while (isRunning)
        {
            gameRenderer.TESTSHOWMENU();
        }

    }
  
}
