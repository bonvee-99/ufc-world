package ui.components.panels.mainframe;

import ui.UfcGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logo extends JPanel implements ActionListener {

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    private UfcGUI gui;

    private JButton themeButton;

    public Logo(UfcGUI gui) {
        this.gui = gui;
        initializeUfcLogo(createUfcTitleIcon(), createUfcCreditName());
    }

    private void initializeUfcLogo(JLabel ufcIcon, JLabel creditName) {
        this.setLayout(null);
        this.setBounds(0, 0, 800, 120);
        this.setBorder(redBorder);
        this.add(ufcIcon);
        this.add(creditName);
        createThemeButton();
    }

    // MODIFIES: this
    // EFFECTS: creates a theme changer button and adds to this
    private void createThemeButton() {
        themeButton = new JButton("Change Theme");
        themeButton.addActionListener(this);
        themeButton.setBounds(640, 60, 150, 50);
        this.add(themeButton);
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
        ImageIcon ufcTitle = createImageIcon("../../images/ufc-logo-small-2.png", "Ufc logo");
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

    // EFFECTS:

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.changeTheme();
    }
}
