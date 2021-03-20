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
        this.setLayout(new GridLayout(9, 1, 0, 5));
        buttons = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: changes theme
    public void changeTheme() {

    }
}
