package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Console based UI inspired by the tellerApp
public class UfcApp {
    private final Scanner userInput;
    UfcWorld myWorld;
    JsonReader jsonReader;
    JsonWriter jsonWriter;

    private String activeWorldDir;

    private static final String JSON_STORE1 = "./data/world1.json";
    private static final String JSON_STORE2 = "./data/world2.json";
    private static final String JSON_STORE3 = "./data/world3.json";
    private static final String JSON_STORE4 = "./data/world4.json";
    private static final String JSON_STORE5 = "./data/world5.json";

    // EFFECTS: runs the UFC application
    public UfcApp() throws FileNotFoundException {
        userInput = new Scanner(System.in);
        initializeUFC(); // either creates or loads in new world
        runUFC();
    }

    // MODIFIES: this
    // EFFECTS: interacts with user input
    private void runUFC() {
        boolean remainRunning = true;
        String command;

        while (remainRunning) {
            displayStartMenu();
            command = userInput.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                remainRunning = confirmQuit();
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user input from menu
    private void processCommand(String command) {
        if (command.equals("c")) {
            createFighter();
        } else if (command.equals("w")) {
            lookAtClass();
        } else if (command.equals("g")) {
            generateFight();
        } else if (command.equals("r")) {
            generateRandomFight();
        } else if (command.equals("s")) {
            saveWorld();
        } else if (command.equals("l")) {
            System.out.println("Please choose which which world you would like to load");
            pickWorld();
            jsonReader = new JsonReader(activeWorldDir);
            loadWorld();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // EFFECTS: save the UfcWorld to file
    public void saveWorld() {
        System.out.println("Please choose which file you would like to save the world to");
        System.out.println("Warning: saving will override previously saved world in file");
        pickWorld();
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
    // EFFECTS: loads UfcWorld from file
    public void loadWorld() {
        try {
            myWorld = jsonReader.read();
            System.out.println("Loaded " + myWorld.getName() + " to " + activeWorldDir);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + activeWorldDir);
        }
    }

    // EFFECTS: displays start menu with given options
    private void displayStartMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("-c-         Create my own fighter         -c-");
        System.out.println("-w-        Look at a weight class         -w-");
        System.out.println("-g-            Generate a fight           -g-");
        System.out.println("-r-        Generate a random fight        -r-");
        System.out.println("-s-              Save World               -s-");
        System.out.println("-l-          Load in a New World          -l-");
        System.out.println("-q-                 Leave                 -q-");
    }

    // MODIFIES: this
    // EFFECTS: handles user input to generate a random fight between to fighters
    private void generateRandomFight() {
        System.out.println("\nPlease choose the weight class of the two fighters:");
        WeightClass selectedWeightClass = selectWeightClass();
        Fighter fighter = selectedWeightClass.getRandomFighter();

        Fighter opponent  = selectedWeightClass.chooseOpponent(fighter);
        System.out.println("\nFighter 1: " + fighter.getName());
        System.out.println("Fighter 2: " + opponent.getName());

        userInput.nextLine(); // added in for bug
        String name = selectFightName(selectedWeightClass);

        System.out.println("\n" +  selectedWeightClass.getNextFight(fighter, opponent, name).getSummary());
    }

    // MODIFIES: this
    // EFFECTS: handles user input to generate a fight between to fighters within the same weight class
    private void generateFight() {
        System.out.println("\nPlease choose the weight class of the two fighters:");
        WeightClass selectedWeightClass = selectWeightClass();

        System.out.println("\nPlease choose your first fighter");
        userInput.nextLine();
        Fighter fighterA = chooseFighter(selectedWeightClass);

        System.out.println("\nPlease choose your second fighter");
        Fighter fighterB = chooseFighter(selectedWeightClass);

        while (fighterA == fighterB) {
            System.out.println("!!! !!! !!!");
            System.out.println("\nA person cannot fight themselves!");
            System.out.println("Please choose a different opponent!");
            fighterB = chooseFighter(selectedWeightClass);
        }

        String fightName = selectFightName(selectedWeightClass);

        System.out.println("\n" + selectedWeightClass.getNextFight(fighterA, fighterB,
                fightName).getSummary());
    }

    // EFFECTS: gets user to pick a fighter or quit if there are none
    private Fighter chooseFighter(WeightClass weightClass) {
        String selection = "";
        Fighter fighter = null;
        while (fighter == null) {
            if (!(selection.equals(""))) {
                System.out.println("Invalid choice");
            }
            System.out.println("\nPlease input the fighter's name");
            System.out.println(weightClass.listFighters());

            selection = userInput.nextLine();
            fighter = weightClass.getFighterByName(selection);

        }
        return fighter;
    }

    // EFFECTS: handles user input to look at information within a weight class
    private void lookAtClass() {
        System.out.println("Please choose which weight division you would like to look at:");
        WeightClass selectedWeightClass = selectWeightClass();
        String selection = "";
        while (!(selection.equals("f") || selection.equals("g") || selection.equals("m") || selection.equals("s"))) {
            if (!(selection.equals(""))) {
                System.out.println("Invalid choice");
            }
            System.out.println("-f- List all fighters in this weight division -f-");
            System.out.println("-g-            Get a fighter's stats          -g-");
            System.out.println("-m- List all recent matches in this division  -m-");
            System.out.println("-s-       Get a summary for a given fight     -s-");

            selection = userInput.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("f")) {
            System.out.println(selectedWeightClass.listFighters());
        } else if (selection.equals("g")) {
            getFighterStats(selectedWeightClass);
        } else if (selection.equals("m")) {
            System.out.println(selectedWeightClass.listFights());
        } else {
            getSummaryFight(selectedWeightClass);
        }
    }

    // EFFECTS: gets user to to pick a fighter by name and it will return the fighter's stats
    private void getFighterStats(WeightClass weightClass) {
        System.out.println("Please choose the fighter you would like to look at");
        userInput.nextLine();
        Fighter fighter = chooseFighter(weightClass);

        System.out.println(fighter.getStats());
    }

    // EFFECTS: gets user to pick a fight by name and it will return a summary of the fight
    private void getSummaryFight(WeightClass weightClass) {
        userInput.nextLine();
        String selection = "";
        Fight fight = null;
        while (fight == null && !selection.equals("q")) {
            if (!(selection.equals(""))) {
                System.out.println("Invalid choice");
            }
            System.out.println("\nPlease input the name of the fight "
                    + "you wish to see a summary of from the following list:");
            System.out.println("If you wish to quit this window press q"); // Allows you to exit if there are no fights
            System.out.println(weightClass.listFights());

            selection = userInput.nextLine();

            fight = weightClass.getFightByName(selection);
        }
        if (selection.equals("q")) {
            System.out.println("Generate some fights!");
        } else {
            System.out.println(fight.getSummary());
        }
    }

    // EFFECTS: gets user to confirm if they want to quit the application
    private boolean confirmQuit() {
        String selection = "";
        while (!(selection.equals("y") || (selection.equals("n")))) {
            if (!(selection.equals(""))) {
                System.out.println("Invalid choice");
            }
            System.out.println("\nAre you sure you want to quit?");
            System.out.println("All progress will be lost unless you saved!");
            System.out.println("-y- Yes -y-");
            System.out.println("-n- No  -n-");

            selection = userInput.next();
            selection = selection.toLowerCase();
        }

        return (selection.equals("n"));
    }

    // MODIFIES: this
    // EFFECTS: handles creating a fighter
    private void createFighter() {
        System.out.println("\nPlease choose your fighter's weight division:");
        WeightClass selectedWeightClass = selectWeightClass();

        userInput.nextLine(); // added in for a bug

        String selectedName = selectFighterName(selectedWeightClass);

        String selectedStance = selectStance();
        Double selectedWeight = selectWeight(selectedWeightClass);
        int selectedHeight = selectHeight();
        int selectedReach = selectReach();
        int selectedAge = selectedAge();
        Fighter fighter = new Fighter(selectedName, selectedStance,
                selectedWeight, selectedHeight, selectedAge, selectedReach);
        selectedWeightClass.addFighter(fighter);

        System.out.println("Congrats here is your fighter! You can find them in their weight class!");
        System.out.println(fighter.getStats());
    }

    // EFFECTS: gets user to input an age for their fighter
    private int selectedAge() {
        int selection = 0;
        while (!(21 <= selection && selection <= 34)) {
            if (!(selection == 0)) {
                System.out.println("Invalid age. UFC fighters need to be at least 21 and at most 34 years old");
            }
            System.out.println("\nPlease input an age for your fighter:");
            System.out.println("Do not input decimal numbers. Valid: 21 Invalid: 25.3");

            selection = userInput.nextInt();
        }

        return selection;
    }

    // EFFECTS: gets user to input a reach for their fighter
    private int selectReach() {
        double selection = 0.0;
        while (!(1.0 <= selection && selection <= 107.0)) {
            if (!(selection == 0.0)) {
                System.out.println("Invalid choice");
            }
            System.out.println("\nPlease input a reach in inches for your fighter:");
            System.out.println("We will round your fighter's reach if you input decimals");

            selection = userInput.nextDouble();
        }

        return (int)Math.round(selection);
    }

    // EFFECTS: gets user to input a name for their fighter
    private String selectFighterName(WeightClass weightClass) {
        String selection = "";
        boolean foundValidFighter = false;
        while (!foundValidFighter) {
            System.out.println("\nPlease create a name for your fighter:");

            selection = userInput.nextLine();

            if (weightClass.getFighterByName(selection) == null && !selection.equals("")) {
                foundValidFighter = true;
            } else {
                System.out.println("This name is taken or is not greater than one character long");
            }
        }
        return selection;
    }

    // EFFECTS: gets user to input a name for the fight
    private String selectFightName(WeightClass weightClass) {
        String selection = "";
        boolean foundValidFight = false;
        while (!foundValidFight) {
            System.out.println("\nPlease create a name for the fight:");
            System.out.println("Remember to make each fight unique");
            System.out.println("Example: Ben VS James 01");


            selection = userInput.nextLine();

            if (weightClass.getFightByName(selection) == null && !selection.equals("")) {
                foundValidFight = true;
            } else {
                System.out.println("\nThis name is taken or is not greater than one character long");
            }
        }
        return selection;
    }

    // EFFECTS: gets user to input their fighter's height in inches
    private int selectHeight() {
        double selection = 0.0;
        while (!(1.0 <= selection && selection <= 107.0)) {
            if (!(selection == 0.0)) {
                System.out.println("Invalid choice");
            }
            System.out.println("\nPlease input a height in inches for your fighter:");
            System.out.println("We will round your fighter's height if you input decimals");

            selection = userInput.nextDouble();
        }

        return (int)Math.round(selection);
    }

    // EFFECTS: gets user to input a valid weight
    private Double selectWeight(WeightClass weightClass) {
        double weight = -1.0;
        while (!(weightClass.getLowerWeightLimit() <= weight
            && weight <= weightClass.getUpperWeightLimit())) {
            if (!(weight == -1.0)) {
                System.out.println("Invalid choice");
            }
            System.out.println("\nPlease input a weight for your fighter between "
                    + weightClass.getLowerWeightLimit() + "lbs " + "and "
                    + weightClass.getUpperWeightLimit() + "lbs:");

            weight = userInput.nextDouble();
        }

        return weight;
    }

    // EFFECTS: gets user to choose between one of the following two stances
    private String selectStance() {
        String selection = "";
        while (!(selection.equals("x") || selection.equals("s"))) {

            if (!(selection.equals(""))) {
                System.out.println("Invalid choice");
            }
            System.out.println("\nPlease choose your fighter's stance:");
            System.out.println("-x- orthodox -x-");
            System.out.println("-s- southpaw -s-");

            selection = userInput.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("x")) {
            return "Orthodox";
        } else {
            return "Southpaw";
        }
    }

    // EFFECTS: gets user to choose one of the following given weight classes
    private WeightClass  selectWeightClass() {
        String selection = "";
        while (!(selection.equals("s") ||  selection.equals("f") || selection.equals("b")
                || selection.equals("e") || selection.equals("l") || selection.equals("w")
                || selection.equals("m") || selection.equals("v") || selection.equals("h"))) {

            if (!(selection.equals(""))) {
                System.out.println("Invalid choice");
            }
            printWeightClassOptions();

            selection = userInput.next();
            selection = selection.toLowerCase();
        }

        return getCorrespondingWeightClass(selection);
    }

    // REQUIRES: choice must be either s f b e l w m or v
    // EFFECTS: gets the corresponding weight class
    private WeightClass getCorrespondingWeightClass(String choice) {
        if (choice.equals("s")) {
            return myWorld.getWeightClassByCode(0);
        } else if (choice.equals("f")) {
            return myWorld.getWeightClassByCode(1);
        } else if (choice.equals("b")) {
            return myWorld.getWeightClassByCode(2);
        } else if (choice.equals("e")) {
            return myWorld.getWeightClassByCode(3);
        } else if (choice.equals("l")) {
            return myWorld.getWeightClassByCode(4);
        } else if (choice.equals("w")) {
            return myWorld.getWeightClassByCode(5);
        } else if (choice.equals("m")) {
            return myWorld.getWeightClassByCode(6);
        } else if (choice.equals("v")) {
            return myWorld.getWeightClassByCode(7);
        } else {
            return myWorld.getWeightClassByCode(8);
        }
    }

    // EFFECTS: prints weight class options to choose from
    private void printWeightClassOptions() {
        System.out.println("-s- Strawweight       -s-");
        System.out.println("-f- Flyweight         -f-");
        System.out.println("-b- Bantamweight      -b-");
        System.out.println("-e- Featherweight     -e-");
        System.out.println("-l- Lightweight       -l-");
        System.out.println("-w- Welterweight      -w-");
        System.out.println("-m- Middleweight      -m-");
        System.out.println("-v- Light Heavyweight -v-");
        System.out.println("-h- Heavyweight       -h-");
    }

    // INITIALIZATION:

    // MODIFIES: this
    // EFFECTS: initializes weight classes and fighters. Also adds fighters into their weight classes.
    private void initializeUFC() {
        String selection = "";
        while (!(selection.equals("a") || selection.equals("b"))) {
            System.out.println("Would you like to either: ");
            System.out.println("-a- Create a new randomly generate UFC world  -a-");
            System.out.println("-b-         Load in a previous world          -b-");

            selection = userInput.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("a")) {
            myWorld = new UfcWorld("world", true);
        } else {
            System.out.println("Please choose which world you would like to open");
            pickWorld();
            jsonReader = new JsonReader(activeWorldDir);
            loadWorld();
        }
    }

    // REQUIRES: each world is already saved and is stored properly
    // EFFECTS: gets user to pick active world
    public void pickWorld() {
        String selection = "";
        while (!(selection.equals("a") || selection.equals("s") || selection.equals("d")
                || selection.equals("f") || selection.equals("g"))) {
            System.out.println("-a- World1 -a-");
            System.out.println("-s- World2 -s-");
            System.out.println("-d- World3 -d-");
            System.out.println("-f- World4 -f-");
            System.out.println("-g- World5 -g-");

            selection = userInput.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("a")) {
            activeWorldDir = JSON_STORE1;
        } else if (selection.equals("s")) {
            activeWorldDir = JSON_STORE2;
        } else if (selection.equals("d")) {
            activeWorldDir = JSON_STORE3;
        } else if (selection.equals("f")) {
            activeWorldDir = JSON_STORE4;
        } else {
            activeWorldDir = JSON_STORE5;
        }
    }
}
