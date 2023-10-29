package com;

import com.UI.Menu;
import javax.swing.JFrame;

/**
 * GameRenderer, responsible for creating our window and creating a new Menu.
 */
public class GameRenderer extends JFrame {
    private int screenWidth = 800;
    private int screenHeight = 500;

    Menu menu = new Menu(this.screenWidth, this.screenHeight);

    private JFrame frame = new JFrame();

    /**
     * Constructor of the GameRenderer Class.
     */
    public GameRenderer() {
        setTitle("BlockGame");

    }

    /**
     * Sets the parameters of the frame.
     */
    public void setupWindow() {
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Makes the window visible.
     */
    public void showMainMenu() {
        frame.setContentPane(this.menu);
    }

}
