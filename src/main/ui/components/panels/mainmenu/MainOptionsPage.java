package ui.components.panels.mainmenu;

import model.Fighter;
import model.WeightClass;
import ui.UfcGUI;
import ui.components.input.UfcButton;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

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

    String name;
    String stance;
    double weight;
    int height;
    int age;
    int reach;

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
        name = chooseFighterName();
        if (!(name == null)) {
            stance = selectStance();
            if (!(stance.equals(""))) {
                weight = selectWeight();
                if (!(weight == -1)) {
                    height = selectHeight();
                    if (!(height == -1)) {
                        reach = selectReach();
                        if (!(reach == -1)) {
                            age = selectAge();
                            if (!(age == -1)) {
                                Fighter fighter = new Fighter(name, stance,
                                        weight, height, age, reach);
                                gui.getActiveWeightClass().addFighter(fighter);
                                gui.updateText("\n" + fighter.getName() + " was added to the current weight class"
                                        + fighter.getStats());
                            }
                        }
                    }
                }
            }
        }
    }



    // EFFECTS: handles user input for choosing a fighter's age
    private int selectAge() {
        int age = -1;
        while (!(21 <= age)) {
            try {
                if (!(age == -1)) {
                    JOptionPane.showMessageDialog(gui, "A fighter needs to be at least 21 years old");
                }
                String ageString = JOptionPane.showInputDialog("Please choose your fighter's age");
                if (ageString == null) {
                    return -1;
                } else {
                    age = Integer.parseInt(ageString);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(gui,"Invalid input type");
                age = -1;
            }
        }
        return age;
    }

    // EFFECTS: handles user input for choosing a fighter's reach
    private int selectReach() {
        double reach = -1;
        while (!(1.0 <= reach && reach <= 107.0)) {
            try {
                if (!(reach == -1)) {
                    JOptionPane.showMessageDialog(gui, "Please choose a valid height");
                }
                String heightString = JOptionPane.showInputDialog("Please choose your fighter's reach (inches)"
                        + "\nWe will round it to the nearest inch");
                if (heightString == null) {
                    return -1;
                } else {
                    reach = Double.parseDouble(heightString);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(gui,"Invalid input type");
                reach = -1;
            }
        }
        return (int)Math.round(reach);
    }


    // EFFECTS: handles user input for choosing a fighter's height
    private int selectHeight() {
        double height = -1;
        while (!(1.0 <= height && height <= 107.0)) {
            try {
                if (!(height == -1)) {
                    JOptionPane.showMessageDialog(gui, "Please choose a valid height");
                }
                String heightString = JOptionPane.showInputDialog("Please choose your fighter's height (inches)"
                        + "\nWe will round it to the nearest inch");
                if (heightString == null) {
                    return -1;
                } else {
                    height = Double.parseDouble(heightString);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(gui,"Invalid input type");
                height = -1;
            }
        }
        return (int)Math.round(height);
    }

    // EFFECTS: handles user input for choosing a fighter's weight
    private double selectWeight() {
        WeightClass wc = gui.getActiveWeightClass();
        double weight = -1;

        while (!(wc.getLowerWeightLimit() <= weight && weight <= wc.getUpperWeightLimit())) {
            try {
                if (!(weight == - 1)) {
                    JOptionPane.showMessageDialog(gui,"Please choose a weight in the given range (lbs)");
                }
                String weightString = JOptionPane.showInputDialog("Please choose your fighter's weight"
                        + "\n" + wc.getLowerWeightLimit() + "-" + wc.getUpperWeightLimit());
                if (weightString == null) {
                    return -1;
                } else {
                    weight = Double.parseDouble(weightString);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(gui,"Invalid input type");
                weight = -1;
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
                playSound();
            } else {
                JOptionPane.showMessageDialog(gui, "Fight name is taken!");
                name = "";
            }
        }
    }

    // EFFECTS: plays bell sound when fight occurs
    // SOURCE: https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
    private void playSound() {
        File file = new File("./data/boxing-bell-2.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-8.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(gui, "ding ding ding");
        }
    }
}
