package com.UI;

import java.awt.Color;
import javax.swing.JButton;

/**
 * A Button for the level selection.
 */
public class LevelButton extends JButton {

    /**
     * Constructor for the LevelButton.
     * 
     * @param level the level to which the button leads.
     */
    public LevelButton(String level) {
        super(level);

        setForeground(Color.blue);
        setFocusPainted(false);
        setBackground(Color.blue);
        setContentAreaFilled(false);
    }
}