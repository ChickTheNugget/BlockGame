package com.View;


import javax.swing.*;
import java.awt.*;
// import javax.imageio.ImageIO;
import java.util.jar.JarEntry;


public class GameRenderer extends JFrame
{
    private int screenWidth = 500;
    private int screenHeight = 500;

    private JFrame frame = new JFrame();

    public GameRenderer()
    {
        setTitle("BlockGame");
    
    }

    public void setupWindow()
    {
        System.out.println("1");
        
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    }


    public void showMainMenu()
    {
        JPanel totalMenu = new JPanel();
        totalMenu.setLayout(null);

        //title
        JPanel textPanel = new JPanel();
        textPanel.setLocation(screenWidth / 3 - 70, screenHeight / 5);
        textPanel.setSize(300, 100);
        textPanel.setBackground(Color.GRAY);
        totalMenu.add(textPanel);

        JLabel label = new JLabel("BlockGame");
        label.setFont(new Font("Serif", Font.PLAIN, 50));
        textPanel.add(label);
        
        // Creation of a label to contain all the JButtons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(10, 80);
        buttonPanel.setSize(250, 70);
        totalMenu.add(buttonPanel);

        //JButton redButton = new JButton("Red Score!");
        // redButton.setLocation(0, 0);
        // redButton.setSize(100, 30);
        // buttonPanel.add(redButton);


        totalMenu.setOpaque(true);
        frame.setContentPane(totalMenu);

    }
 
}
