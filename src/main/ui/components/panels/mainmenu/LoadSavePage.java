package ui.components.panels.mainmenu;

import model.UfcWorld;
import ui.UfcGUI;
import ui.components.input.UfcButton;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoadSavePage extends MainMenu {

    private UfcGUI gui;
    private UfcButton newWorldButton;
    private UfcButton loadWorldButton;

    public LoadSavePage(UfcGUI gui) {
        super();
        this.gui = gui;
        initializeButtons();
        this.setLayout(new GridLayout(2, 1, 0, 0));
    }

    // MODIFIES: this, gui
    // EFFECTS: initializes buttons and adds them to this
    private void initializeButtons() {
        newWorldButton = new UfcButton("Create a new random UFC world");
        newWorldButton.addActionListener(this);
        buttons.add(newWorldButton);
        this.add(newWorldButton);

        loadWorldButton = new UfcButton("Load in a previous UFC world");
        loadWorldButton.addActionListener(this);
        buttons.add(loadWorldButton);
        this.add(loadWorldButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newWorldButton) {
            gui.setMyWorld(new UfcWorld("world", true));
            gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(4));
            gui.updateText("\n!Active weight class: lightweight!");
            gui.addWeightDivisionButtons();
            gui.setMenuAsMainOptionsPage();
        } else if (e.getSource() == loadWorldButton) {
            gui.setMenuAsLoadFromOptionsPage();
        }
    }
}
