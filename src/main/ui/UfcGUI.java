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
import java.util.ArrayList;
import java.util.List;

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

    List<MainMenu> menuPanels;

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

    static final Color darkBack = Color.black;
    static final Color darkText = Color.lightGray;
    static final Color lightBack = Color.lightGray;
    static final Color lightText = Color.black;
    static final Color myBack = Color.decode("#CF6679");
    static final Color myText = Color.decode("#121212");


    public UfcGUI() {
        initializeMainPanels();
        addStartingPanels();
        initializeMainJFrame();
        changeTheme();
    }

    // MODIFIES: this
    // EFFECTS: initializes all panels
    private void initializeMainPanels() {
        logo = new Logo(this);
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
        printLog.setBorder(BorderFactory.createLineBorder(Color.decode("#d44446"), 1));
        printLog.setFont(new Font("Comic Sans",Font.PLAIN,15));
        JScrollPane scrollPane = new JScrollPane(printLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuLog.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates all the MainMenu panels
    private void initializeMainMenuPanels() {
        menuPanels = new ArrayList<>();
        loadSavePage = new LoadSavePage(this);
        menuPanels.add(loadSavePage);
        mainOptionsPage = new MainOptionsPage(this);
        menuPanels.add(mainOptionsPage);
        loadFromOptionsPage = new LoadFromOptionsPage(this);
        menuPanels.add(loadFromOptionsPage);
        saveToOptionsPage = new SaveToOptionsPage(this);
        menuPanels.add(saveToOptionsPage);
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
        this.setSize(1000, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white); // doesn't matter but hurt eyes while creating it
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: updates current text in print pane
    public void updateText(String text) {
        currentText += "\n" + "-->" + text;
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
    // EFFECTS: prints list of fights in current active weight class
    public void printFights() {
        String fightList = activeWeightClass.listFightSummaries();
        updateText(fightList);
    }


    // MODIFIES: this
    // EFFECTS: changes theme
    public void changeTheme() {
        if (theme == 0) {
            setLightTheme();
            theme = 1;
        } else if (theme == 1) {
            setDarkTheme();
            theme = 0;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes theme to light theme
    public void setLightTheme() {
        logo.setBackground(lightBack);
        menuLog.setBackground(lightBack);
        menu.setBackground(lightBack);
        printLog.setBackground(lightBack);
        printLog.setForeground(lightText);

        for (MainMenu panel : menuPanels) {
            panel.changeTheme(lightBack, lightText);
        }
        weightClassPanel.changeTheme(lightBack, lightText);
    }

    // MODIFIES: this
    // EFFECTS: changes theme to dark theme
    public void setDarkTheme() {
        logo.setBackground(darkBack);
        menuLog.setBackground(darkBack);
        menu.setBackground(darkBack);
        printLog.setBackground(darkBack);
        printLog.setForeground(darkText);

        for (MainMenu panel : menuPanels) {
            panel.changeTheme(darkBack, darkText);
        }
        weightClassPanel.changeTheme(darkBack, darkText);
    }

    // GETTERS:

    public UfcWorld getMyWorld() {
        return this.myWorld;
    }

    public WeightClass getActiveWeightClass() {
        return this.activeWeightClass;
    }

    // SETTERS:

    public void setActiveWeightClass(WeightClass newActiveWeightClass) {
        activeWeightClass = newActiveWeightClass;
    }

    public void setMyWorld(UfcWorld world) {
        myWorld = world;
    }
}


