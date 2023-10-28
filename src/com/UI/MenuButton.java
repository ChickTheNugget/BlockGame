package com.UI;

import java.awt.*;
import javax.swing.*;

public class MenuButton extends JButton {

    private int radius;

    public MenuButton(String label, int radius) {
        super(label);
        this.radius = radius;

        setForeground(Color.white);
        setFocusPainted(false);
        setBackground(Color.LIGHT_GRAY);
    }
}
