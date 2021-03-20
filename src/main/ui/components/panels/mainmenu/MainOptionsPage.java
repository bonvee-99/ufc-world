package ui.components.panels.mainmenu;

import ui.UfcGUI;
import ui.components.input.UfcButton;

import java.awt.*;
import java.awt.event.ActionEvent;

// main options panel
public class MainOptionsPage extends MainMenu {

    UfcButton createFightButton;
    UfcButton listFightersButton;
    UfcButton getFightersStatsButton;
    UfcButton listFightsButton;
    UfcButton genFight;
    UfcButton genRandomFight;
    UfcButton saveWorldButton;
    UfcButton loadWorldButton;
    UfcButton quitButton;


    UfcGUI gui;

    public MainOptionsPage(UfcGUI gui) {
        super();
        initializeButtons();
        this.gui = gui;
        this.setLayout(new GridLayout(9, 1, 0, 0));
    }

    // MODIFIES: this
    // EFFECTS: creates buttons and adds them to this
    private void initializeButtons() {
        createFightButton = new UfcButton("Create my own fighter");
        listFightersButton = new UfcButton("List Fighters");
        getFightersStatsButton = new UfcButton("Get a Fighter's Stats");
        listFightsButton = new UfcButton("List Fight Summaries");
        genFight = new UfcButton("Generate a fight");
        genRandomFight = new UfcButton("Generate a a random fight");
        saveWorldButton = new UfcButton("Save World");
        loadWorldButton = new UfcButton("Load World");
        quitButton = new UfcButton("Leave");
        addButtonsToButtons();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons to buttons
    private void addButtonsToButtons() {
        buttons.add(createFightButton);
        buttons.add(listFightersButton);
        buttons.add(getFightersStatsButton);
        buttons.add(listFightsButton);
        buttons.add(genFight);
        buttons.add(genRandomFight);
        buttons.add(saveWorldButton);
        buttons.add(loadWorldButton);
        buttons.add(quitButton);
    }

    // MODIFIES: this
    // EFFECTS: loops through button list and adds action listener and adds it to this
    public void addButtons() {
        for (UfcButton button : buttons) {
            button.addActionListener(this);
            this.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        if (button == listFightersButton) {
            gui.printFighters();
        } else if (button == listFightsButton) {
            gui.printFights();
        } else if (button == loadWorldButton) {
            gui.setMenuAsLoadFromOptionsPage();
        } else if (button == saveWorldButton) {
            gui.setMenuAsSaveToOptionsPage();
        }
    }
}
