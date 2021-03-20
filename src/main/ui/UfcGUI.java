package ui;

import model.UfcWorld;
import model.WeightClass;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.panels.mainframe.WeightClassPanel;
import ui.components.panels.mainmenu.*;
import ui.components.panels.mainframe.Logo;
import ui.components.panels.mainframe.MenuLog;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// interactive GUI Ufc World
public class UfcGUI extends JFrame {

    // main panels
    MainMenu menu;
    Logo logo;
    WeightClassPanel weightClassPanel;

    // menu log stuff
    MenuLog menuLog;
    JTextArea printLog;
    String currentText;

    // menu panels
    LoadSavePage loadSavePage;
    MainOptionsPage mainOptionsPage;
    LoadFromOptionsPage loadFromOptionsPage;
    SaveToOptionsPage saveToOptionsPage;

    // ufc world stuff
    UfcWorld myWorld;
    WeightClass activeWeightClass;

    // SAVE/LOAD stuff
    private String activeWorldDir;

    private static final String JSON_STORE1 = "./data/world1.json";
    private static final String JSON_STORE2 = "./data/world2.json";
    private static final String JSON_STORE3 = "./data/world3.json";
    private static final String JSON_STORE4 = "./data/world4.json";
    private static final String JSON_STORE5 = "./data/world5.json";

    // Theme:
    private int theme;

    public UfcGUI() {
        initializeMainPanels();
        addStartingPanels();
        initializeMainJFrame();
    }

    // MODIFIES: this
    // EFFECTS: initializes all panels
    private void initializeMainPanels() {
        logo = new Logo();
        weightClassPanel = new WeightClassPanel(this);
        initializeMenuLog();
        initializeMainMenuPanels();
    }

    // MODIFIES: this
    // EFFECTS: initializes menu log and adds a scrollable text area that outputs messages
    private void initializeMenuLog() {
        menuLog = new MenuLog();
        currentText = "";
        printLog = new JTextArea();
        printLog.setText("");
        printLog.setEditable(false);
        printLog.setLineWrap(true);
        printLog.setSize(new Dimension(270, 530));
        printLog.setBorder(BorderFactory.createLineBorder(Color.decode("#d44446"), 3));
        printLog.setFont(new Font("Comic Sans",Font.PLAIN,15));
        JScrollPane scrollPane = new JScrollPane(printLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuLog.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates all the MainMenu panels
    private void initializeMainMenuPanels() {
        loadSavePage = new LoadSavePage(this);
        mainOptionsPage = new MainOptionsPage(this);
        loadFromOptionsPage = new LoadFromOptionsPage(this);
        saveToOptionsPage = new SaveToOptionsPage(this);
    }

    // MODIFIES: this
    // EFFECTS: adds starting panels to this. makes starting menu = loadSavePage
    private void addStartingPanels() {
        menu = loadSavePage;
        this.add(menu);
        this.add(logo);
        this.add(menuLog);
        this.add(weightClassPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes main JFrame settings
    public void initializeMainJFrame() {
        this.setTitle("Ufc World");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // lets you exit out of application on close
        this.setResizable(false);
        this.setSize(800, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white); // doesn't matter but hurt eyes while creating it
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: updates current text in print pane
    public void updateText(String text) {
        currentText += text;
        printLog.setText(currentText);
        printLog.setCaretPosition(printLog.getDocument().getLength()); // scrolls down to most recent text
        // source:
        // https://stackoverflow.com/questions/2483572/making-a-jscrollpane-automatically-scroll-all-the-way-down
    }

    public void addWeightDivisionButtons() {
        weightClassPanel.enableButtons();
    }

    // MODIFIES: this
    // EFFECTS: sets menu as main options page
    public void setMenuAsMainOptionsPage() {
        this.remove(menu);
        menu = mainOptionsPage;
        this.add(menu);
        loadNewMenu();
    }

    // MODIFIES: this
    // EFFECTS: sets menu as load from options page
    public void setMenuAsLoadFromOptionsPage() {
        this.remove(menu);
        menu = loadFromOptionsPage;
        this.add(menu);
        loadNewMenu();
    }

    public void setMenuAsSaveToOptionsPage() {
        this.remove(menu);
        menu = saveToOptionsPage;
        this.add(menu);
        loadNewMenu();
    }

    // MODIFIES: this
    // EFFECTS: repaints menu with new components
    private void loadNewMenu() {
        menu.revalidate();
        menu.repaint();
    }

    // MODIFIES: this
    // EFFECTS: sets active world directory to options correspondent
    public void setActiveDir(int option) {
        if (option == 1) {
            activeWorldDir = JSON_STORE1;
        } else if (option == 2) {
            activeWorldDir = JSON_STORE2;
        } else if (option == 3) {
            activeWorldDir = JSON_STORE3;
        } else if (option == 4) {
            activeWorldDir = JSON_STORE4;
        } else if (option == 5) {
            activeWorldDir = JSON_STORE5;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ufc world from file
    public void loadWorld() {
        JsonReader jsonReader = new JsonReader(activeWorldDir);
        try {
            myWorld = jsonReader.read();
            updateText("\nLoaded " + myWorld.getName() + " from " + activeWorldDir);
        } catch (IOException e) {
            updateText("\nUnable to read from file: " + activeWorldDir);
        }
    }

        // EFFECTS: saves world to selected UFC file directory
    public void saveWorld() {
        JsonWriter jsonWriter = new JsonWriter(activeWorldDir);
        try {
            jsonWriter.open();
            jsonWriter.write(myWorld);
            jsonWriter.close();
            updateText("\nSaved " + myWorld.getName() + " to " + activeWorldDir);
        } catch (FileNotFoundException e) {
            updateText("\nUnable to save to file: " + activeWorldDir);
        }
    }

    // MODIFIES: this
    // EFFECTS: prints list of fighters in current active weight class
    public void printFighters() {
        String fighterList = activeWeightClass.listFighters();
        updateText(fighterList);
    }

    // MODIFIES: this
    // EFFECTS: changes theme
    public void changeTheme() {
        if (theme == 0) {
            setLightTheme();
            theme = 1;
        } else if (theme == 1) {
            setWackoTheme();
            theme = 2;
        } else if (theme == 2) {
            setDarkTheme();
            theme = 0;
        }
        logo.setBackground(darkBack);
        menuLog.setBackground(darkBack);
        menu.setBackground(darkBack);
        printLog.setBackground(darkBack);
        printLog.setForeground(darkText);
    }


    // GETTERS:

    public UfcWorld getMyWorld() {
        return this.myWorld;
    }

    // SETTERS:

    public void setActiveWeightClass(WeightClass newActiveWeightClass) {
        activeWeightClass = newActiveWeightClass;
    }

    public void setMyWorld(UfcWorld world) {
        myWorld = world;
    }
//    JPanel menu;
//    MenuLog menuLog; // shows activity/prints stuff
//    Logo logo;
//    WeightClassPanel weightClassPanel;
//    Set<JButton> buttons;
//
//    JTextArea printLog;
//    String currentText;
//
//    Boolean isDark;
//    static final Color darkBack = Color.black;
//    static final Color darkText = Color.lightGray;
//    static final Color lightBack = Color.lightGray;
//    static final Color lightText = Color.black;
//
//    Boolean save;
//
//    JButton lightDarkButton;
//    JButton newWorldButton;
//    JButton loadWorldButton;
//    JButton world1;
//    JButton world2;
//    JButton world3;
//    JButton world4;
//    JButton world5;
//    JButton createFightButton;
//    JButton lookAtDivision;
//    JButton genFight;
//    JButton genRandomFight;
//    JButton saveWorldButton;
//    JButton leaveButton;
//
//    JButton strawW;
//    JButton flyW;
//    JButton bantamW;
//    JButton featherW;
//    JButton lightW;
//    JButton welterW;
//    JButton middleW;
//    JButton lightHeavyW;
//    JButton heavyW;
//
//    WeightClass activeWeightClass;
//
//    UfcWorld myWorld;
//    JsonReader jsonReader;
//    JsonWriter jsonWriter;
//
//    private String activeWorldDir;
//
//    // New fields:
//    LoadSavePanel loadsavePanel;
//    //
//
//    private static final String JSON_STORE1 = "./data/world1.json";
//    private static final String JSON_STORE2 = "./data/world2.json";
//    private static final String JSON_STORE3 = "./data/world3.json";
//    private static final String JSON_STORE4 = "./data/world4.json";
//    private static final String JSON_STORE5 = "./data/world5.json";
//
//    private static final Border redBorder = BorderFactory.createLineBorder(Color.decode("#d44446"), 3);
//
//    // EFFECTS: creates GUI for ufc world
//    public UfcGUI() {
//        initializeButtons();
//        initializeStartingMenu();
//        initializeWeightClassOptions();
//        initializeMenuLog();
//        logo = new Logo();
//
//        isDark = true;
//        setTheme();
//        lightDarkButton.setBounds(640, 60, 150, 50);
//        logo.add(lightDarkButton);
//
//        initializeMainMenuJFrame();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: creates button options for selected weight class
//    private void initializeWeightClassOptions() {
//        weightClassPanel = new WeightClassPanel(this);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes all buttons and adds them to buttons
//    private void initializeButtons() {
//        buttons = new HashSet<>();
//
//        addStartMenuButtons();
//
//        lightDarkButton = new UfcButton("Dark Theme", this);
//        buttons.add(lightDarkButton);
//
//        addWorldButtons();
//
//        addMainMenuButtons();
//
//        addWeightOptionButtons();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds weight option buttons
//    private void addWeightOptionButtons() {
//        strawW = new UfcButton("strawweight", this);
//        buttons.add(strawW);
//        flyW = new UfcButton("flyweight", this);
//        buttons.add(flyW);
//        bantamW = new UfcButton("bantamweight", this);
//        buttons.add(bantamW);
//        featherW = new UfcButton("featherweight", this);
//        buttons.add(featherW);
//        lightW = new UfcButton("lightweight", this);
//        buttons.add(lightW);
//        welterW = new UfcButton("welterweight", this);
//        buttons.add(welterW);
//        middleW = new UfcButton("middleweight", this);
//        buttons.add(middleW);
//        lightHeavyW = new UfcButton("light heavyweight", this);
//        buttons.add(lightHeavyW);
//        heavyW = new UfcButton("heavyweight", this);
//        buttons.add(heavyW);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds start menu buttons and sets save to false
//    private void addStartMenuButtons() {
//        save = false;
//        newWorldButton = new UfcButton("Create a a new random UFC world", this);
//        buttons.add(newWorldButton);
//        loadWorldButton = new UfcButton("Load in a previous UFC world", this);
//        buttons.add(loadWorldButton);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds main menu buttons
//    private void addMainMenuButtons() {
//        createFightButton = new UfcButton("Create my own fighter", this);
//        buttons.add(createFightButton);
//        lookAtDivision = new UfcButton("Look at a weight class", this);
//        buttons.add(lookAtDivision);
//        genFight = new UfcButton("Generate a fight", this);
//        buttons.add(genFight);
//        genRandomFight = new UfcButton("Generate a a random fight", this);
//        buttons.add(genRandomFight);
//        saveWorldButton = new UfcButton("Save World", this);
//        buttons.add(saveWorldButton);
//        leaveButton = new UfcButton("Leave", this);
//        buttons.add(leaveButton);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds load world buttons;
//    private void addWorldButtons() {
//        world1 = new UfcButton("World1", this);
//        buttons.add(world1);
//        world2 = new UfcButton("World2", this);
//        buttons.add(world2);
//        world3 = new UfcButton("World3", this);
//        buttons.add(world3);
//        world4 = new UfcButton("World4", this);
//        buttons.add(world4);
//        world5 = new UfcButton("World5", this);
//        buttons.add(world5);
//    }
//
//    // EFFECTS: handles all button events
//    // source: stackoverflow
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object cb = e.getSource();
//        if (cb == newWorldButton) {
//            myWorld = new UfcWorld("world", true);
//            activeWeightClass = myWorld.getWeightClassByCode(4);
//            updateText("\n!Active weight class: lightweight!");
//            addWeightOptionsToPanel();
//            loadMainMenu();
//        } else if (cb == loadWorldButton) {
//            chooseWorld();
//        } else if (cb == world1 || cb == world2 || cb == world3
//                || cb == world4 || cb == world5) {
//            if (save) {
//                handleSaveButton(cb);
//                save = false;
//            } else {
//                handleLoadButton(cb);
//            }
//        } else if (cb == lightDarkButton) {
//            setTheme();
//        } else if (cb == saveWorldButton) {
//            save = true;
//            chooseWorld();
//        }
//    }
//
//
//
//    // MODIFIES: this
//    // EFFECTS: prints what the active weight class is now
//    private void shoutActiveClass(Object cb) {
//        if (cb == strawW) {
//            updateText("\n!Active weight class: strawweight!");
//        } else if (cb == flyW) {
//            updateText("\n!Active weight class: flyweight!");
//        } else if (cb == bantamW) {
//            updateText("\n!Active weight class: bantamweight!");
//        } else if (cb == featherW) {
//            updateText("\n!Active weight class: featherweight!");
//        } else if (cb == lightW) {
//            updateText("\n!Active weight class: lightweight!");
//        } else if (cb == welterW) {
//            updateText("\n!Active weight class: welterweight!");
//        } else if (cb == middleW) {
//            updateText("\n!Active weight class: middleweight!");
//        } else if (cb == lightHeavyW) {
//            updateText("\n!Active weight class: light heavyweight!");
//        } else if (cb == heavyW) {
//            updateText("\n!Active weight class: heavy weight!");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: saves world to selected choice
//    private void handleSaveButton(Object button) {
//        if (button == world1) {
//            activeWorldDir = JSON_STORE1;
//        } else if (button == world2) {
//            activeWorldDir = JSON_STORE2;
//        } else if (button == world3) {
//            activeWorldDir = JSON_STORE3;
//        } else if (button == world4) {
//            activeWorldDir = JSON_STORE4;
//        } else if (button == world5) {
//            activeWorldDir = JSON_STORE5;
//        }
//        saveWorld();
//        loadMainMenu();
//    }
//
//    // EFFECTS: saves world to selected UFC file directory
//    private void saveWorld() {
//        jsonWriter = new JsonWriter(activeWorldDir);
//        try {
//            jsonWriter.open();
//            jsonWriter.write(myWorld);
//            jsonWriter.close();
//            updateText("\nSaved " + myWorld.getName() + " to " + activeWorldDir);
//        } catch (FileNotFoundException e) {
//            updateText("\nUnable to save to file: " + activeWorldDir);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: changes theme to light or dark
//    private void setTheme() {
//        if (!isDark) {
//            setDarkTheme();
//        } else {
//            setLightTheme();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: changes theme to dark theme
//    private void setDarkTheme() {
//        for (JButton button: buttons) {
//            button.setForeground(darkText);
//            button.setBackground(darkBack);
//        }
//        logo.setBackground(darkBack);
//        menuLog.setBackground(darkBack);
//        menu.setBackground(darkBack);
//        printLog.setBackground(darkBack);
//        printLog.setForeground(darkText);
//        isDark = true;
//        lightDarkButton.setText("Light Theme");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: changes theme to light theme
//    private void setLightTheme() {
//        for (JButton button: buttons) {
//            button.setForeground(lightText);
//            button.setBackground(lightBack);
//        }
//        logo.setBackground(lightBack);
//        menu.setBackground(lightBack);
//        menuLog.setBackground(lightBack);
//        printLog.setBackground(lightBack);
//        printLog.setForeground(lightText);
//        isDark = false;
//        lightDarkButton.setText("Dark Theme");
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: handles the five load buttons
//    public void handleLoadButton(Object button) {
//        if (button == world1) {
//            activeWorldDir = JSON_STORE1;
//        } else if (button == world2) {
//            activeWorldDir = JSON_STORE2;
//        } else if (button == world3) {
//            activeWorldDir = JSON_STORE3;
//        } else if (button == world4) {
//            activeWorldDir = JSON_STORE4;
//        } else if (button == world5) {
//            activeWorldDir = JSON_STORE5;
//        }
//        loadWorld();
//        activeWeightClass = myWorld.getWeightClassByCode(4);
//        updateText("\n!Active weight class: lightweight!");
//        addWeightOptionsToPanel();
//        loadMainMenu();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: makes menu a list of 7 buttons to choose from
//    private void loadMainMenu() {
//        clearMenu();
//        menu.add(createFightButton);
//        menu.add(lookAtDivision);
//        menu.add(genFight);
//        menu.add(genRandomFight);
//        menu.add(saveWorldButton);
//        menu.add(loadWorldButton);
//        this.loadNewMenu();
//    }
//
//    // MODIFIES: this
//    // EFFECT: loads ufc world from file
//    private void loadWorld() {
//        jsonReader = new JsonReader(activeWorldDir);
//        try {
//            myWorld = jsonReader.read();
//            updateText("\nLoaded " + myWorld.getName() + " from " + activeWorldDir);
//        } catch (IOException e) {
//            updateText("\nUnable to read from file: " + activeWorldDir);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: makes menu buttons 5 different worlds to choose to load. loads choice
//    private void chooseWorld() {
//        clearMenu();
//        menu.add(world1);
//        menu.add(world2);
//        menu.add(world3);
//        menu.add(world4);
//        menu.add(world5);
//
//        this.loadNewMenu();
//    }
//
//
//
//    // MODIFIES: this
//    // EFFECTS: clears menu panel buttons
//    private void clearMenu() {
//        menu.removeAll();
//    }
//
//    // EFFECTS: creates main menu panel and adds starting buttons
//    public void initializeStartingMenu() {
//        menu = new MainMenu();
//        menu.add(newWorldButton);
//        menu.add(loadWorldButton);
//    }
//
//    // EFFECTS: initializes menu log panel
//    public void initializeMenuLog() {
//        menuLog = new MenuLog();
//        menuLog.setLayout(new BorderLayout());
//        printLog = new JTextArea();
//        currentText = " ";
//        printLog.setText(currentText);
//        printLog.setEditable(false);
//        printLog.setLineWrap(true);
//        printLog.setSize(new Dimension(260, 530));
//        printLog.setBorder(redBorder);
//        printLog.setFont(new Font("Comic Sans",Font.PLAIN,15));
//        JScrollPane scrollPane = new JScrollPane(printLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        menuLog.add(scrollPane, BorderLayout.CENTER);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: updates current text in print pane
//    public void updateText(String text) {
//        currentText += text;
//        printLog.setText(currentText);
//        printLog.setCaretPosition(printLog.getDocument().getLength()); // source: stackoverflow
//        // https://stackoverflow.com/questions/2483572/making-a-jscrollpane-automatically-scroll-all-the-way-down
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds all starting components to "this", and creates a whole JFrame menu
//    private void initializeMainMenuJFrame() {
//        this.add(logo);
//        this.add(menu);
//        this.add(menuLog);
//        this.add(weightClassPanel);
//
//        // JFrame settings:
//        this.setTitle("Ufc World");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // lets you exit out of application on close
//        this.setResizable(false);
//        this.setSize(800, 700);
//        this.setLayout(null);
//        this.getContentPane().setBackground(Color.white); // doesn't matter but hurt eyes while creating it
//        this.setVisible(true);
//    }
    // TODO:
    /*
    - instantiate a bunch of menu panels (within these panels you have the buttons, you also have
    the method the loops through the list of buttons within it)
    - when you want to change screens

    - if you need to access fields within UfcGUI just create getters/setters so you can do it within
    the panel class!
    . also will need to pass in the ufc gui to the panel class.

    this will make your gui have less code (wont have to instantiate all the buttons, wont have to handle events...)
     */
}


