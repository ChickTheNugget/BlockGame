package com.View;

import javax.swing.*;

public class GameRenderer extends JFrame
{
    private int screenWidth = 500;
    private int screenHeight = 500;


    public GameRenderer()
    {
        setupWindow(screenHeight, screenWidth);
        setTitle("BlockGame");
    }

    private void setupWindow(int screenHeight, int screenWidth)
    {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        setSize(screenWidth, screenHeight);
    }
    public void showWindow()
    {
        setVisible(true);
    }
}
