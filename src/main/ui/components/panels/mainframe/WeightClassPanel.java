package ui.components.panels.mainframe;

import ui.UfcGUI;
import ui.components.input.UfcButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WeightClassPanel extends JPanel implements ActionListener {

    UfcButton strawW;
    UfcButton flyW;
    UfcButton bantamW;
    UfcButton featherW;
    UfcButton lightW;
    UfcButton welterW;
    UfcButton middleW;
    UfcButton lightHeavyW;
    UfcButton heavyW;

    UfcGUI gui;

    private List<UfcButton> buttons;

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    public WeightClassPanel(UfcGUI gui) {
        this.setBounds(297, 117, 170, 555);
        this.setBorder(redBorder);
        this.setLayout(new GridLayout(9, 1, 0, 0));
        this.gui = gui;
        buttons = new ArrayList<>();
        initializeWeightOptions();
        disableButtons();
    }

    // MODIFIES: this
    // EFFECTS: disables all buttons
    private void disableButtons() {
        for (UfcButton button : buttons) {
            button.setEnabled(false);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates all weight division buttons and adds them to this
    public void initializeWeightOptions() {
        strawW = new UfcButton("StrawWeight");
        strawW.addActionListener(this);
        flyW = new UfcButton("FlyWeight");
        flyW.addActionListener(this);
        bantamW = new UfcButton("BantamWeight");
        bantamW.addActionListener(this);
        featherW = new UfcButton("FeatherWeight");
        featherW.addActionListener(this);
        lightW = new UfcButton("LightWeight");
        lightW.addActionListener(this);
        welterW = new UfcButton("WelterWeight");
        welterW.addActionListener(this);
        middleW = new UfcButton("MiddleWeight");
        middleW.addActionListener(this);
        lightHeavyW = new UfcButton("LightHeavyWeight");
        lightHeavyW.addActionListener(this);
        heavyW = new UfcButton("HeavyWeight");
        heavyW.addActionListener(this);

        addButtons();
        addButtonsToButtons();
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to this
    private void addButtons() {
        this.add(strawW);
        this.add(flyW);
        this.add(bantamW);
        this.add(featherW);
        this.add(lightW);
        this.add(welterW);
        this.add(middleW);
        this.add(lightHeavyW);
        this.add(heavyW);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons to buttons
    private void addButtonsToButtons() {
        buttons.add(strawW);
        buttons.add(flyW);
        buttons.add(bantamW);
        buttons.add(featherW);
        buttons.add(lightW);
        buttons.add(welterW);
        buttons.add(middleW);
        buttons.add(lightHeavyW);
        buttons.add(heavyW);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        if (button == strawW) {
            handleStrawW();
        } else if (button == flyW) {
            handleFlyW();
        } else if (button == bantamW) {
            handleBantamW();
        } else if (button == featherW) {
            handleFeatherW();
        } else if (button == lightW) {
            handleLightW();
        } else if (button == welterW) {
            handleWelterW();
        } else if (button == middleW) {
            handleMiddleW();
        } else if (button == lightHeavyW) {
            handleLightHeavyW();
        } else if (button == heavyW) {
            handleHeavyW();
        }
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleStrawW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(0));
        gui.updateText("\n!Active weight class: StrawWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleFlyW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(1));
        gui.updateText("\n!Active weight class: FlyWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleBantamW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(2));
        gui.updateText("\n!Active weight class: BantamWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleFeatherW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(3));
        gui.updateText("\n!Active weight class: FeatherWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleLightW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(4));
        gui.updateText("\n!Active weight class: LightWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleWelterW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(5));
        gui.updateText("\n!Active weight class: WelterWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleMiddleW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(6));
        gui.updateText("\n!Active weight class: MiddleWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleLightHeavyW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(7));
        gui.updateText("\n!Active weight class: LightHeavyWeight!");
    }

    // MODIFIES: gui
    // EFFECTS: changes active weight class and updates text field
    public void handleHeavyW() {
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(8));
        gui.updateText("\n!Active weight class: HeavyWeight!");
    }

    // MODIFIES: this
    // EFFECTS: enables all buttons
    public void enableButtons() {
        for (UfcButton button : buttons) {
            button.setEnabled(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes button background and text to given colours
    public void changeTheme(Color backColor, Color textColor) {
        for (UfcButton button : buttons) {
            button.setBackground(backColor);
            button.setForeground(textColor);
        }
        this.setBackground(backColor);
    }
}
