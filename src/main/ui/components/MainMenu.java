package ui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainMenu extends JPanel {

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    public MainMenu() {
        this.setBounds(0, 117, 400, 555);
        this.setBorder(redBorder);
        this.setLayout(new GridLayout(7, 1, 0, 0));
    }
}
