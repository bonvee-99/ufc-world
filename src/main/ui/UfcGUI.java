package ui;

import model.UfcWorld;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// interactive GUI Ufc World
public class UfcGUI extends JFrame implements ActionListener {

    JPanel menu;
    JPanel menuLog; // shows activity/prints stuff
    JPanel logo;

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);


    // EFFECTS:
    public UfcGUI() {

        menu = new JPanel();



        menuLog = new JPanel();




        ImageIcon ufcTitle = createImageIcon("images/ufc-logo-small-2.png", "Ufc logo");
        JLabel menuLabel = new JLabel();
        menuLabel.setIcon(ufcTitle);
        menuLabel.setBounds(0, 0, 289, 100);
        menuLabel.setVerticalAlignment(JLabel.CENTER);

        logo = new JPanel();
        logo.setBackground(Color.decode("#d5b6ab"));
        logo.setBounds(0, 0, 800, 120);
        logo.setBorder(redBorder);
        logo.add(menuLabel);


        // Add all components to "this":
        this.add(logo);
        this.add(menu);
        this.add(menuLog);

        // JFrame settings:
        this.setTitle("Ufc World");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // lets you exit out of application on close
        this.setResizable(false);
        this.setSize(800, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK); // doesn't matter but hurt eyes while creating it

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hi");
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

/*
overview plan:

split screen in 2. activity log on the right. menu on the left.

get the menu to constantly change with the buttons you press.
Have one menu (set as a PANEL with buttons/text inputs).
each input can create a new menu...?
 */
