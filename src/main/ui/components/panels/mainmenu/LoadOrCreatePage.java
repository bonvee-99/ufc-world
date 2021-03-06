package ui.components.panels.mainmenu;

import model.UfcWorld;
import ui.UfcGUI;
import ui.components.input.UfcButton;

import java.awt.*;
import java.awt.event.ActionEvent;

// load or save panel
public class LoadOrCreatePage extends MainMenu {

    private UfcButton newWorldButton;
    private UfcButton loadWorldButton;
    private UfcButton newEmptyWorldButton;

    public LoadOrCreatePage(UfcGUI gui) {
        super(gui);
        initializeButtons();
        this.setLayout(new GridLayout(3, 1, 0, 0));
    }

    // MODIFIES: this, gui
    // EFFECTS: initializes buttons and adds them to this
    private void initializeButtons() {
        newWorldButton = new UfcButton("Create a new random UFC world");
        newWorldButton.addActionListener(this);
        buttons.add(newWorldButton);
        this.add(newWorldButton);

        newEmptyWorldButton = new UfcButton("Create a new empty UFC world");
        newEmptyWorldButton.addActionListener(this);
        buttons.add(newEmptyWorldButton);
        this.add(newEmptyWorldButton);

        loadWorldButton = new UfcButton("Load in a previous UFC world");
        loadWorldButton.addActionListener(this);
        buttons.add(loadWorldButton);
        this.add(loadWorldButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newWorldButton) {
            gui.setMyWorld(new UfcWorld("world", 2));
            gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(4));
            gui.updateText("\n!Active weight class: lightweight!");
            gui.addWeightDivisionButtons();
            gui.setMenuAsMainOptionsPage();
        } else if (e.getSource() == newEmptyWorldButton) {
            gui.setMyWorld(new UfcWorld("world", 1));
            gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(4));
            gui.updateText("\n!Active weight class: lightweight!");
            gui.updateText("\nPlease add some fighters");
            gui.addWeightDivisionButtons();
            gui.setMenuAsMainOptionsPage();
        } else if (e.getSource() == loadWorldButton) {
            gui.setMenuAsLoadFromOptionsPage();
        }
    }
}
