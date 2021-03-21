package ui.components.panels.mainmenu;

import com.sun.codemodel.internal.JOp;
import model.Fighter;
import model.WeightClass;
import ui.UfcGUI;
import ui.components.input.UfcButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// main options panel
public class MainOptionsPage extends MainMenu {

    UfcButton createFighterButton;
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
        createFighterButton = new UfcButton("Create my own fighter");
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
        buttons.add(createFighterButton);
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
        } else if (button == quitButton) {
            System.exit(0);
        } else if (button == genRandomFight) {
            handleRandomFight();
        } else if (button == genFight) {
            handleGenerateFight();
        } else if (button == getFightersStatsButton) {
            handleStatsButton();
        } else if (button == createFighterButton) {
            handleCreateFighterButton();
        }
    }

    // MODIFIES: this
    // EFFECTS: handles user input for creating a fighter
    private void handleCreateFighterButton() {
        String name = chooseFighterName();
        String stance;
        double weight;
        if (!(name == null)) {
            stance = selectStance();
            if (!(stance.equals(""))) {
                weight = selectWeight();
                if (!(weight == -1)) {
                    //
                }
            }
        }
        // height
        // reach
        // age
    }

    // EFFECTS: handles user input for choosing a fighter's weight
    private double selectWeight() {
        WeightClass wc = gui.getActiveWeightClass();
        double weight = -1;

        while (!(wc.getLowerWeightLimit() <= weight && weight <= wc.getUpperWeightLimit())) {
            try {
                if (!(weight == - 1)) {
                    JOptionPane.showMessageDialog(gui,"Please choose a weight in the given range");
                }
                String weightString = JOptionPane.showInputDialog("Please choose your fighter's weight"
                        + "\n" + wc.getLowerWeightLimit() + "-" + wc.getUpperWeightLimit());
                if (weightString == null) {
                    return -1;
                } else {
                    weight = Double.parseDouble(weightString);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(gui,"Invalid input");
            }
        }
        return weight;
    }

    // EFFECTS: handles user input for choosing a fighter's stance
    private String selectStance() {
        // source: https://mkyong.com/swing/java-swing-joptionpane-showoptiondialog-example/
        String[] options = {"Orthodox", "Southpaw"};

        int choice = JOptionPane.showOptionDialog(gui, "Choose your fighter's stance", "Stance",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            return "Orthodox";
        } else if (choice == 1) {
            return "Southpaw";
        } else {
            return "";
        }
    }

    // EFFECTS: gets user to choose fighter's name
    private String chooseFighterName() {
        WeightClass wc = gui.getActiveWeightClass();
        boolean foundValidFighter = false;
        String name = "";
        while (!foundValidFighter) {
            name = JOptionPane.showInputDialog("Please create your fighter's name:");
            if (name == null) {
                JOptionPane.showMessageDialog(gui, "Canceled");
                break;
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(gui, "Invalid name");
            } else if (wc.getFighterByName(name) == null) {
                foundValidFighter = true;
            } else {
                JOptionPane.showMessageDialog(gui, "name is taken!");
            }
        }
        return name;
    }

    // MODIFIES: this
    // EFFECTS: prints fighters stats
    private void handleStatsButton() {
        Fighter fighter = chooseFighter();

        if (!(fighter == null)) {
            gui.updateText(fighter.getStats());
        }
    }

    // MODIFIES: gui
    // EFFECTS: handles input for generating a fight
    private void handleGenerateFight() {
        Fighter fighterA;
        Fighter fighterB;

        fighterA = chooseFighter();
        if (!(fighterA == null)) {
            fighterB = chooseFighter();
            while (fighterA == fighterB) {
                JOptionPane.showMessageDialog(gui, "A fighter cannot fight themselves!");
                fighterB = chooseFighter();
            }
            if (!(fighterB == null)) {
                chooseFightName(fighterA, fighterB);
            }
        }
    }

    // EFFECTS: gets user to choose a fighter from active weight class
    private Fighter chooseFighter() {
        WeightClass wc = gui.getActiveWeightClass();
        String name = "";
        Fighter fighter = null;
        while (fighter == null) {
            name = JOptionPane.showInputDialog("Please choose a fighter from the given options"
                    + wc.listFighters());

            if (name == null) {
                JOptionPane.showMessageDialog(gui, "Canceled!");
                break;
            }
            fighter = wc.getFighterByName(name);
        }
        return fighter;
    }

    // MODIFIES: this, gui
    // EFFECTS: handles input for generating a random fight
    private void handleRandomFight() {
        WeightClass wc = gui.getActiveWeightClass();
        Fighter fighterA = wc.getRandomFighter();
        Fighter fighterB = wc.chooseOpponent(fighterA);
        chooseFightName(fighterA, fighterB);
    }

    // REQUIRES: neither fighter is null
    // MODIFIES: this
    // EFFECTS: gets user to choose fight name
    private void chooseFightName(Fighter fighterA, Fighter fighterB) {
        WeightClass wc = gui.getActiveWeightClass();
        String name = "";
        while (name.equals("")) {
            name = JOptionPane.showInputDialog("\n" + fighterA.getName()
                    + "\n" + fighterB.getName()
                    + "\nWhat would you like to name the fight?", null);
            if (name == null) {
                JOptionPane.showMessageDialog(gui, "Fight was canceled!");
                break;
            } else if (wc.getFightByName(name) == null && !name.equals("")) {
                gui.updateText("\n" + wc.getNextFight(fighterA, fighterB, name).getSummary());
                // adds fight to history and displays result
                gui.updateText("\n added: \"" + name + "\" to match history");
            } else {
                JOptionPane.showMessageDialog(gui, "Fight name is taken!");
                name = "";
            }
        }
    }


}
