package ui.components.panels.mainmenu;

import ui.components.input.UfcButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class MainMenu extends JPanel implements ActionListener {

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    protected List<UfcButton> buttons;

    public MainMenu() {
        this.setBounds(0, 117, 300, 555);
        this.setBorder(redBorder);
        buttons = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: changes button background and text to given colours
    public void changeTheme(Color backColor, Color textColor) {
        for (UfcButton button : buttons) {
            button.setBackground(backColor);
            button.setForeground(textColor);
        }
    }
}
