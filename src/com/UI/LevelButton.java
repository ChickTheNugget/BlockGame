package com.UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class LevelButton extends JButton {
    int radius;

    public LevelButton(String label, int radius) {
        super(label);
        this.radius = radius;

        setForeground(Color.blue);
        setFocusPainted(false);
        setBackground(Color.blue);
        setContentAreaFilled(false);
    }
}