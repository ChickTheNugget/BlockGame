package com.View;


import javax.swing.*;
import java.awt.*;
// import javax.imageio.ImageIO;


public class GameRenderer extends JFrame
{
    private int screenWidth = 500;
    private int screenHeight = 500;
    private Graphics graphicsFrameBuffer;


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

    public void TESTSHOWMENU()
    {
        showMainMenu(graphicsFrameBuffer);
    }

    private void showMainMenu(Graphics graphicsFrameBuffer)
    {
        drawText(graphicsFrameBuffer, "BlockGame", screenWidth/2, 200, 90, true);
        drawText(graphicsFrameBuffer, "by Nguyen Jean and Mil Majerus", screenWidth/2, 220, 20, true);
    
    }
    private void drawText(Graphics graphicsFrameBuffer, String msg, int xPosition, int yPosition, int size, boolean center)
    {
        float factor = Math.min(screenWidth, screenHeight);
        graphicsFrameBuffer.setFont(new Font("TimesRoman", Font.PLAIN, (int) (size * factor)));
        if (center)
        {
            xPosition -= graphicsFrameBuffer.getFontMetrics().stringWidth(msg) / 2;
            yPosition -= size / 2;
        }
        graphicsFrameBuffer.drawString(msg, (int)(100), (int)(100));
    }
}
