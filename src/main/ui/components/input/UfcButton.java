package ui.components.input;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UfcButton extends JButton {

    public UfcButton(String text) {
        this.setText(text);
        this.setFont(new Font("Comic Sans",Font.BOLD,15));
        this.setOpaque(true);
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.decode("#d44446")));
    }
}
