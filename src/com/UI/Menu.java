package com.UI;

import com.GameLogic.GamePanel;
import com.GameLogic.Sound;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The MenuManager, displays and changes the different menus and launches games.
 */
public class Menu extends JPanel implements ActionListener {

    public static String levelSelect = "levelSelect";
    public static String menuSelect = "menuSelect";

    private final Color backgroundColor;

    int screenWidth;
    int screenHeight;

    private JPanel currentScreen;
    private JPanel mainMenu;
    private JPanel levelScreen;

    Sound sound = new Sound();

    /**
     * Constructor for the Menu.
     * Sets the Layout to Null and initializes the screen to a given width and
     * height.
     * Additionally it prepares our screens and sets the titlescreen.
     * 
     * @param screenWidth  the width of the screeen
     * @param screenHeight the height of the screen
     */
    public Menu(int screenWidth, int screenHeight) {
        backgroundColor = Color.lightGray;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setLayout(null);

        setBackground(backgroundColor);
        prepareMainMenu();
        prepareLevelSelection();
        this.currentScreen = mainMenu;

        this.add(currentScreen);
    }

    /**
     * Creates the panel for the title screen.
     */
    private void prepareMainMenu() {
        playMusic(1);
        Label title = new Label("BlockGame");
        title.setFont(new Font("Sans Serif", Font.PLAIN, 50));
        title.setBackground(backgroundColor);
        title.setAlignment(Label.CENTER);
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(2, 1));
        panel.setLocation(screenHeight / 2 - 50, screenHeight / 2);
        panel.setSize(screenWidth / 2, 80);
        panel.add(title);

        MenuButton levelButton = new MenuButton("Levels");
        levelButton.setActionCommand(levelSelect);
        levelButton.addActionListener(this);
        levelButton.setLocation(0, 0);
        levelButton.setSize(100, 30);
        panel.add(levelButton);

        panel.setOpaque(false);
        mainMenu = panel;
    }

    /**
     * Creates the panel for the level selection screen.
     */
    private void prepareLevelSelection() {
        levelScreen = new JPanel();
        levelScreen.setLayout(new BoxLayout(levelScreen, BoxLayout.PAGE_AXIS));
        levelScreen.add(Box.createGlue());
        levelScreen.setSize(screenWidth, screenHeight);

        JPanel topRow = new JPanel();
        topRow.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        MenuButton backButton = new MenuButton("Back");
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

        LevelButton level1 = new LevelButton("1");
        level1.setActionCommand("level1");
        level1.addActionListener(this);
        levels.add(level1, constraints);

        LevelButton level2 = new LevelButton("2");
        level2.setActionCommand("level2");
        level2.addActionListener(this);
        levels.add(level2, constraints);

        LevelButton level3 = new LevelButton("3");
        level3.setActionCommand("level3");
        level3.addActionListener(this);
        levels.add(level3, constraints);

        LevelButton level4 = new LevelButton("4");
        level4.setActionCommand("level4");
        level4.addActionListener(this);
        levels.add(level4, constraints);

        LevelButton level5 = new LevelButton("5");
        level5.setActionCommand("level5");
        level5.addActionListener(this);
        levels.add(level5, constraints);

        levels.setBackground(backgroundColor);
        levelScreen.add(levels);
        levelScreen.add(Box.createGlue());
    }

    /**
     * Handles the buttonpress events, switches to the wanted screen
     * or launches a game instance.
     */
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
                GamePanel panel = new GamePanel("com/assets/levels/wall1.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level2".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("com/assets/levels/wall2.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level3".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("com/assets/levels/wall3.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level4".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("com/assets/levels/wall4.txt", frame);
                frame.add(panel);
                panel.startThread();
            } else if ("level5".equals(e.getActionCommand())) {
                GamePanel panel = new GamePanel("com/assets/levels/wall5.txt", frame);
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

    /**
     * Changes the screen currently displayed.
     * 
     * @param screen the new screen
     */
    private void changeScreen(JPanel screen) {
        this.remove(this.currentScreen);
        currentScreen = screen;
        this.add(currentScreen);
    }

    /**
     * Starts playing a sound on repeat.
     * 
     * @param soundIndex the index of the to be played sound
     *                   in the list of sounds of our Sound instance
     */
    public void playMusic(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
        sound.loop();
    }

    /**
     * Stops a currently palying sound.
     */
    public void stopMusic() {
        sound.stop();
    }

    /**
     * Plays a sound from its Sound instance once.
     * 
     * @param soundIndex the index of the to be played sound
     *                   in the list of sounds of our Sound instance
     */
    public void playClip(int soundIndex) {
        sound.setFile(soundIndex);
        sound.play();
    }

}
