package ui;

import model.UfcWorld;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.Logo;
import ui.components.MenuLog;
import ui.components.MainMenu;
import ui.components.UfcButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// interactive GUI Ufc World
public class UfcGUI extends JFrame implements ActionListener {
    MainMenu menu;
    MenuLog menuLog; // shows activity/prints stuff
    Logo logo;
    Set<JButton> buttons;

    JTextArea printLog;
    String currentText;

    Boolean isDark;
    static final Color darkBack = Color.black;
    static final Color darkText = Color.lightGray;
    static final Color lightBack = Color.lightGray;
    static final Color lightText = Color.black;

    Boolean save;

    JButton lightDarkButton;
    JButton newWorldButton;
    JButton loadWorldButton;
    JButton world1;
    JButton world2;
    JButton world3;
    JButton world4;
    JButton world5;
    JButton createFightButton;
    JButton lookAtDivision;
    JButton genFight;
    JButton genRandomFight;
    JButton saveWorldButton;
    JButton leaveButton;

    UfcWorld myWorld;
    JsonReader jsonReader;
    JsonWriter jsonWriter;

    private String activeWorldDir;

    private static final String JSON_STORE1 = "./data/world1.json";
    private static final String JSON_STORE2 = "./data/world2.json";
    private static final String JSON_STORE3 = "./data/world3.json";
    private static final String JSON_STORE4 = "./data/world4.json";
    private static final String JSON_STORE5 = "./data/world5.json";

    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);

    // EFFECTS: creates GUI for ufc world
    public UfcGUI() {
        initializeButtons();
        initializeStartingMenu();
        initializeMenuLog();
        logo = new Logo();

        isDark = true;
        setTheme();
        lightDarkButton.setBounds(640, 60, 150, 50);
        logo.add(lightDarkButton);

        initializeMainMenuJFrame();
    }

    // MODIFIES: this
    // EFFECTS: initializes all buttons and adds them to buttons
    private void initializeButtons() {
        buttons = new HashSet<>();

        addStartMenuButtons();

        lightDarkButton = new UfcButton("Dark Theme", this);
        buttons.add(lightDarkButton);

        addWorldButtons();

        addMainMenuButtons();
    }

    // MODIFIES: this
    // EFFECTS: adds start menu buttons
    private void addStartMenuButtons() {
        save = false;
        newWorldButton = new UfcButton("Create a new randomly generated UFC world", this);
        buttons.add(newWorldButton);
        loadWorldButton = new UfcButton("Load in a previous UFC world", this);
        buttons.add(loadWorldButton);
    }

    // MODIFIES: this
    // EFFECTS: adds main menu buttons
    private void addMainMenuButtons() {
        createFightButton = new UfcButton("Create my own fighter", this);
        buttons.add(createFightButton);
        lookAtDivision = new UfcButton("Look at a weight class", this);
        buttons.add(lookAtDivision);
        genFight = new UfcButton("Generate a fight", this);
        buttons.add(genFight);
        genRandomFight = new UfcButton("Generate a a random fight", this);
        buttons.add(genRandomFight);
        saveWorldButton = new UfcButton("Save World", this);
        buttons.add(saveWorldButton);
        leaveButton = new UfcButton("Leave", this);
        buttons.add(leaveButton);
    }

    // MODIFIES: this
    // EFFECTS: adds load world buttons;
    private void addWorldButtons() {
        world1 = new UfcButton("World1", this);
        buttons.add(world1);
        world2 = new UfcButton("World2", this);
        buttons.add(world2);
        world3 = new UfcButton("World3", this);
        buttons.add(world3);
        world4 = new UfcButton("World4", this);
        buttons.add(world4);
        world5 = new UfcButton("World5", this);
        buttons.add(world5);
    }

    // EFFECTS: handles all button events
    // source: stackoverflow
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newWorldButton) {
            myWorld = new UfcWorld("world", true);
            loadMainMenu();
        } else if (e.getSource() == loadWorldButton) {
            chooseWorld();
        } else if (e.getSource() == world1 || e.getSource() == world2 || e.getSource() == world3
                || e.getSource() == world4 || e.getSource() == world5) {
            if (save) {
                handleSaveButton(e.getSource());
                save = false;
            } else {
                handleLoadButton(e.getSource());
            }
        } else if (e.getSource() == lightDarkButton) {
            setTheme();
        } else if (e.getSource() == saveWorldButton) {
            save = true;
            chooseWorld();
        }
    }

    // MODIFIES: this
    // EFFECTS: saves world to selected choice
    private void handleSaveButton(Object button) {
        if (button == world1) {
            activeWorldDir = JSON_STORE1;
        } else if (button == world2) {
            activeWorldDir = JSON_STORE2;
        } else if (button == world3) {
            activeWorldDir = JSON_STORE3;
        } else if (button == world4) {
            activeWorldDir = JSON_STORE4;
        } else if (button == world5) {
            activeWorldDir = JSON_STORE5;
        }
        saveWorld();
        loadMainMenu();
    }

    // EFFECTS: saves world to selected UFC file directory
    private void saveWorld() {
        jsonWriter = new JsonWriter(activeWorldDir);
        try {
            jsonWriter.open();
            jsonWriter.write(myWorld);
            jsonWriter.close();
            System.out.println("Saved " + myWorld.getName() + " to " + activeWorldDir);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + activeWorldDir);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes theme to light or dark
    private void setTheme() {
        if (!isDark) {
            setDarkTheme();
        } else {
            setLightTheme();
        }
    }

    // MODIFIES: this
    // EFFECTS: changes theme to dark theme
    private void setDarkTheme() {
        for (JButton button: buttons) {
            button.setForeground(darkText);
            button.setBackground(darkBack);
        }
        logo.setBackground(darkBack);
        menuLog.setBackground(darkBack);
        menu.setBackground(darkBack);
        printLog.setBackground(darkBack);
        printLog.setForeground(darkText);
        isDark = true;
        lightDarkButton.setText("Light Theme");
    }

    // MODIFIES: this
    // EFFECTS: changes theme to light theme
    private void setLightTheme() {
        for (JButton button: buttons) {
            button.setForeground(lightText);
            button.setBackground(lightBack);
        }
        logo.setBackground(lightBack);
        menu.setBackground(lightBack);
        menuLog.setBackground(lightBack);
        printLog.setBackground(lightBack);
        printLog.setForeground(lightText);
        isDark = false;
        lightDarkButton.setText("Dark Theme");
    }


    // MODIFIES: this
    // EFFECTS: handles the five load buttons
    public void handleLoadButton(Object button) {
        if (button == world1) {
            activeWorldDir = JSON_STORE1;
        } else if (button == world2) {
            activeWorldDir = JSON_STORE2;
        } else if (button == world3) {
            activeWorldDir = JSON_STORE3;
        } else if (button == world4) {
            activeWorldDir = JSON_STORE4;
        } else if (button == world5) {
            activeWorldDir = JSON_STORE5;
        }
        loadWorld();
        loadMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: makes menu a list of 7 buttons to choose from
    private void loadMainMenu() {
        clearMenu();
        menu.add(createFightButton);
        menu.add(lookAtDivision);
        menu.add(genFight);
        menu.add(genRandomFight);
        menu.add(saveWorldButton);
        menu.add(loadWorldButton);
        this.loadNewMenu();
    }

    // MODIFIES: this
    // EFFECT: loads ufc world from file
    private void loadWorld() {
        jsonReader = new JsonReader(activeWorldDir);
        try {
            myWorld = jsonReader.read();
            System.out.println("Loaded " + myWorld.getName() + " from " + activeWorldDir);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + activeWorldDir);
        }
    }

    // MODIFIES: this
    // EFFECTS: makes menu buttons 5 different worlds to choose to load. loads choice
    private void chooseWorld() {
        clearMenu();
        menu.add(world1);
        menu.add(world2);
        menu.add(world3);
        menu.add(world4);
        menu.add(world5);

        this.loadNewMenu();
    }

    // MODIFIES: this
    // EFFECTS: repaints menu with new components
    private void loadNewMenu() {
        menu.revalidate();
        menu.repaint();
    }

    // MODIFIES: this
    // EFFECTS: clears menu panel buttons
    private void clearMenu() {
        menu.removeAll();
    }

    // EFFECTS: creates main menu panel and adds starting buttons
    public void initializeStartingMenu() {
        menu = new MainMenu();
        menu.add(newWorldButton);
        menu.add(loadWorldButton);
    }

    // EFFECTS: initializes menu log panel
    public void initializeMenuLog() {
        menuLog = new MenuLog();
        menuLog.setLayout(new BorderLayout());
        printLog = new JTextArea();
        currentText = "\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n "
                + "hi\n hi\n hi\n hi\n hi"
                + "\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi\n hi";
        printLog.setText(currentText);
        printLog.setEditable(false);
        printLog.setLineWrap(true);
        printLog.setSize(new Dimension(260, 530));
        printLog.setBorder(redBorder);
        printLog.setFont(new Font("Comic Sans",Font.PLAIN,15));
        JScrollPane scrollPane = new JScrollPane(printLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuLog.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: updates current text in print pane
    public void updateText(String text) {
        currentText += text;
        printLog.setText(currentText);
        printLog.setCaretPosition(printLog.getDocument().getLength()); // source: stackoverflow
        // https://stackoverflow.com/questions/2483572/making-a-jscrollpane-automatically-scroll-all-the-way-down
    }

    // MODIFIES: this
    // EFFECTS: adds all starting components to "this", and creates a whole JFrame menu
    private void initializeMainMenuJFrame() {
        this.add(logo);
        this.add(menu);
        this.add(menuLog);

        // JFrame settings:
        this.setTitle("Ufc World");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // lets you exit out of application on close
        this.setResizable(false);
        this.setSize(800, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white); // doesn't matter but hurt eyes while creating it
        this.setVisible(true);
    }
}


