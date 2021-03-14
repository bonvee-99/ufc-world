package ui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Logo extends JPanel {

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    public Logo() {
        initializeUfcLogo(createUfcTitleIcon(), createUfcCreditName());

    }

    private void initializeUfcLogo(JLabel ufcIcon, JLabel creditName) {
        this.setLayout(null);
        this.setBounds(0, 0, 800, 120);
        this.setBorder(redBorder);
        this.add(ufcIcon);
        this.add(creditName);
    }

    private JLabel createUfcCreditName() {
        JLabel nameLabel = new JLabel();
        nameLabel.setText("By Ben Vinnick");
        nameLabel.setBounds(670, 0, 150, 50);
        nameLabel.setFont(new Font("Comic Sans",Font.BOLD,15));
        nameLabel.setForeground(Color.red);
        return nameLabel;
    }

    private JLabel createUfcTitleIcon() {
        ImageIcon ufcTitle = createImageIcon("images/ufc-logo-small-2.png", "Ufc logo");
        JLabel menuLabel = new JLabel();
        menuLabel.setIcon(ufcTitle);
        menuLabel.setBounds(255, 10, 289, 100);
        return menuLabel;
    }

    // EFFECTS: returns image from image path as an ImageIcon or null if not found
    // Source: components-IconDemoProject
    public ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
