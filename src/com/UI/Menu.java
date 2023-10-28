package com.UI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import com.GameLogic.GamePanel;

public class Menu extends JPanel implements ActionListener {

    public static String levelSelect = "levelSelect";
    public static String menuSelect = "menuSelect";

    int screenWidth;
    int screenHeight;

    private JPanel currentScreen;
    private JPanel mainMenu;
    private JPanel levelScreen;

    public Menu(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setLayout(null);

        prepareMainMenu();
        prepareLevelSelection();
        this.currentScreen = mainMenu;

        this.add(currentScreen);
    }

    private void prepareMainMenu() {

        Label title = new Label("BlockGame");
        title.setAlignment(Label.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.setLocation(screenHeight / 2, screenHeight / 2);
        panel.setSize(screenWidth / 3, 80);
        panel.add(title);

        MenuButton levelButton = new MenuButton("Levels", 25);
        levelButton.setActionCommand(levelSelect);
        levelButton.addActionListener(this);
        levelButton.setLocation(0, 0);
        levelButton.setSize(100, 30);
        panel.add(levelButton);

        mainMenu = panel;
    }

    private void prepareLevelSelection() {
        levelScreen = new JPanel();
        levelScreen.setLayout(new BoxLayout(levelScreen, BoxLayout.PAGE_AXIS));
        levelScreen.add(Box.createGlue());
        levelScreen.setSize(screenWidth, screenHeight);

        JPanel topRow = new JPanel();
        topRow.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        MenuButton backButton = new MenuButton("Back", 25);
        backButton.setActionCommand(menuSelect);
        backButton.addActionListener(this);
        backButton.setLocation(10, 10);
        backButton.setSize(50, 30);
        topRow.add(backButton);
        levelScreen.add(topRow);

        JPanel levels = new JPanel();
        levels.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        levels.setSize(screenWidth, screenHeight - 200);

        LevelButton level1 = new LevelButton("1", 25);
        level1.setActionCommand("level1");
        level1.addActionListener(this);
        levels.add(level1, constraints);

        LevelButton level2 = new LevelButton("2", 25);
        levels.add(level2, constraints);

        LevelButton level3 = new LevelButton("3", 25);
        levels.add(level3, constraints);

        LevelButton level4 = new LevelButton("4", 25);
        levels.add(level4, constraints);

        levelScreen.add(levels);
        levelScreen.add(Box.createGlue());
    }

    public void actionPerformed(ActionEvent e) {
        if (levelSelect.equals(e.getActionCommand())) {
            changeScreen(levelScreen);
        } else if (menuSelect.equals(e.getActionCommand())) {
            changeScreen(mainMenu);
        } else if ("level1".equals(e.getActionCommand())) {
            GamePanel panel = new GamePanel("src/com/levels/wall1.txt");
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setTitle("BlockGame");

            frame.add(panel);

            frame.pack();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            panel.startThread();
        }

        this.validate();
        this.repaint();
    }

    private void changeScreen(JPanel screen) {
        this.remove(this.currentScreen);
        currentScreen = screen;
        this.add(currentScreen);
    }

}
