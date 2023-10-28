package com.UI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import com.GameLogic.GamePanel;
import com.GameLogic.Sound;

public class Menu extends JPanel implements ActionListener {

    public static String levelSelect = "levelSelect";
    public static String menuSelect = "menuSelect";

    int screenWidth;
    int screenHeight;

    private JPanel currentScreen;
    private JPanel mainMenu;
    private JPanel levelScreen;

    Sound sound = new Sound();

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
        playMusic(1);
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
        level2.setActionCommand("level2");
        level2.addActionListener(this);
        levels.add(level2, constraints);

        LevelButton level3 = new LevelButton("3", 25);
        level3.setActionCommand("level3");
        level3.addActionListener(this);
        levels.add(level3, constraints);

        LevelButton level4 = new LevelButton("4", 25);
        level4.setActionCommand("level4");
        level4.addActionListener(this);
        levels.add(level4, constraints);

        LevelButton level5 = new LevelButton("5", 25);
        level5.setActionCommand("level5");
        level5.addActionListener(this);
        levels.add(level5, constraints);

        levelScreen.add(levels);
        levelScreen.add(Box.createGlue());
    }

    public void actionPerformed(ActionEvent e) {
        if (levelSelect.equals(e.getActionCommand())) {
            changeScreen(levelScreen);
        } else if (menuSelect.equals(e.getActionCommand())) {
            changeScreen(mainMenu);
        } else {
            JFrame frame = new JFrame();
            frame.setResizable(false);
            frame.setTitle("BlockGame");

            if ("level1".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("src/com/levels/wall1.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level2".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("src/com/levels/wall2.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level3".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("src/com/levels/wall3.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level4".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("src/com/levels/wall4.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level5".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("src/com/levels/wall5.txt", frame);
                frame.add(panel);
                panel.startThread();
            }
            frame.pack();

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }

        this.validate();
        this.repaint();
    }

    private void changeScreen(JPanel screen) {
        this.remove(this.currentScreen);
        currentScreen = screen;
        this.add(currentScreen);
    }

    public void playMusic(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playClip(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
    }

}
