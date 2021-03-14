package ui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenuLog extends JPanel {
    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    public MenuLog() {
        this.setBounds(397, 117, 403, 555);
        this.setBorder(redBorder);
    }
}
