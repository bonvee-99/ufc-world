package ui.components.panels.mainmenu;

import ui.UfcGUI;
import ui.components.input.UfcButton;

import java.awt.*;
import java.awt.event.ActionEvent;

// load from options panel
public class LoadFromOptionsPage extends MainMenu {

    UfcButton world1;
    UfcButton world2;
    UfcButton world3;
    UfcButton world4;
    UfcButton world5;

    public LoadFromOptionsPage(UfcGUI gui) {
        super(gui);
        initializeButtons();
        this.setLayout(new GridLayout(5, 1, 0, 0));
    }

    // MODIFIES: this, gui
    // EFFECTS: initializes buttons and adds them to this
    private void initializeButtons() {
        world1 = new UfcButton("World1");
        world1.addActionListener(this);
        buttons.add(world1);
        this.add(world1);

        world2 = new UfcButton("World2");
        world2.addActionListener(this);
        buttons.add(world2);
        this.add(world2);

        world3 = new UfcButton("World3");
        world3.addActionListener(this);
        buttons.add(world3);
        this.add(world3);

        world4 = new UfcButton("World4");
        world4.addActionListener(this);
        buttons.add(world4);
        this.add(world4);

        world5 = new UfcButton("World5");
        world5.addActionListener(this);
        buttons.add(world5);
        this.add(world5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        if (button == world1) {
            gui.setActiveDir(1);
        } else if (button == world2) {
            gui.setActiveDir(2);
        } else if (button == world3) {
            gui.setActiveDir(3);
        } else if (button == world4) {
            gui.setActiveDir(4);
        } else if (button == world5) {
            gui.setActiveDir(5);
        }
        gui.loadWorld();
        gui.setActiveWeightClass(gui.getMyWorld().getWeightClassByCode(4));
        gui.updateText("\n!Active weight class: lightweight!");
        gui.addWeightDivisionButtons();
        gui.setMenuAsMainOptionsPage();
    }
}
