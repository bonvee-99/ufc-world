package ui.components.panels.mainframe;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

// menu log panel
public class MenuLog extends JPanel {
    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    public MenuLog() {
        this.setBounds(464, 117, 336, 555);
        this.setBorder(redBorder);
        this.setLayout(new BorderLayout());
    }

}
