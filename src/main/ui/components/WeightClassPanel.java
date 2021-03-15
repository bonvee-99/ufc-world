package ui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public class WeightClassPanel extends JPanel {

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    public WeightClassPanel() {
        this.setBounds(297, 117, 200, 555);
        this.setBorder(redBorder);
        this.setLayout(new GridLayout(9, 1, 0, 0));
    }

}
