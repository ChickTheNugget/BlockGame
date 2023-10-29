package com.UI;

import java.awt.*;
import javax.swing.*;

/**
 * A Button which is used to switch screens, this is done by the Menu.
 */
public class MenuButton extends JButton {

    /**
     * The constructor of the MenuButton.
     * 
     * @param label the Label that should be displayed on the Button
     */
    public MenuButton(String label) {
        super(label);

        setForeground(Color.white);
        setFocusPainted(false);
        setBackground(Color.orange);
    }
}
