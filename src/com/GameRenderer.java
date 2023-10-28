package com;

import java.awt.*;
import javax.swing.*;

import com.UI.Menu;

public class GameRenderer extends JFrame {
    private int screenWidth = 800;
    private int screenHeight = 500;

    Menu menu = new Menu(this.screenWidth, this.screenHeight);

    private JFrame frame = new JFrame();

    public GameRenderer() {
        setTitle("BlockGame");

    }

    public void setupWindow() {
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        // frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void showMainMenu() {

        frame.setContentPane(this.menu);

    }

}
