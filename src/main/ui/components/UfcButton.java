package ui.components;

import ui.UfcGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UfcButton extends JButton {

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    public UfcButton(String text, UfcGUI gui) {
        this.setText(text);
        this.addActionListener(gui);
        this.setFont(new Font("Comic Sans",Font.BOLD,15));
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.setOpaque(true);
        this.setBorder(redBorder);
    }
}
