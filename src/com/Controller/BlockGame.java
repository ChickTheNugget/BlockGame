package com.Controller;

import com.View.GameRenderer;

public class BlockGame {
    private GameRenderer gameRenderer;
    public BlockGame()
    {
        gameRenderer = new GameRenderer();
    }
    public void run()
    {
        gameRenderer.showWindow();
    }
}
