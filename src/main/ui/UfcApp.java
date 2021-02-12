package ui;

import model.Fight;
import model.Fighter;
import model.WeightClass;

import java.util.ArrayList;
import java.util.Scanner;

// Console based UI inspired by the tellerApp
public class UfcApp {
    private Scanner userInput;

    private WeightClass strawWeight;
    private WeightClass flyWeight;
    private WeightClass bantamWeight;
    private WeightClass featherWeight;
    private WeightClass lightWeight;
    private WeightClass welterWeight;
    private WeightClass middleWeight;
    private WeightClass lightHeavyWeight;
    private WeightClass heavyWeight;

    private ArrayList<String> names1;
    private ArrayList<String> names2;
    private ArrayList<String> names3;
    private ArrayList<String> names4;
    private ArrayList<String> names5;
    private ArrayList<String> names6;
    private ArrayList<String> names7;
    private ArrayList<String> names8;
    private ArrayList<String> names9;

    private static final String NAME1 = "Tiny Tim";
    private static final String NAME2 = "Joel Hammond";
    private static final String NAME3 = "Rick Feder";
    private static final String NAME4 = "Sam Davids";
    private static final String NAME5 = "Tony Brooks";
    private static final String NAME6 = "Connor Marsh";
    private static final String NAME7 = "Sameer Frey";
    private static final String NAME8 = "Antoni Cain";
    private static final String NAME9 = "Kier Huber";
    private static final String NAME10 = "Connor Marsh";
    private static final String NAME11 = "Kelsey Zuniga";
    private static final String NAME12 = "Osama Lindsey";
    private static final String NAME13 = "Domas Werner";
    private static final String NAME14 = "Raheel Cortex";
    private static final String NAME15 = "Kamran Oakley";
    private static final String NAME16 = "Ronny Russel";
    private static final String NAME17 = "Tyrese Holcomb";
    private static final String NAME18 = "Tyson Foley";
    private static final String NAME19 = "Jasper Workman";
    private static final String NAME20 = "Joss Daniel";
    private static final String NAME21 = "Wil Wilkes";
    private static final String NAME22 = "Yusef Jenkins";
    private static final String NAME23 = "Caine Webber";
    private static final String NAME24 = "Dwayne Cornish";
    private static final String NAME25 = "Carl Rodriquez";
    private static final String NAME26 = "Denzel Hendricks";
    private static final String NAME27 = "Patryk Wade";
    private static final String NAME28 = "Cayden Mcnamara";
    private static final String NAME29 = "Macaulay Bowes";
    private static final String NAME30 = "Maverick Bush";
    private static final String NAME31 = "Kohen Walker";
    private static final String NAME32 = "Sahil Ortega";
    private static final String NAME33 = "Dominic Potter";
    private static final String NAME34 = "Imani Nicholls";
    private static final String NAME35 = "Ronaldo Gallegos";
    private static final String NAME36 = "Lawson Russo";
    private static final String NAME37 = "Gerard Tang";
    private static final String NAME38 = "Matthias Grant";
    private static final String NAME39 = "Hunter Mann";
    private static final String NAME40 = "Charles Petty";
    private static final String NAME41 = "Lucien Sharp";
    private static final String NAME42 = "Saad Redmond";
    private static final String NAME43 = "Kristopher Mcneill";
    private static final String NAME44 = "Dainton Carson";
    private static final String NAME45 = "Emmett Esparza";


    // EFFECTS: runs the UFC application
    public UfcApp() {
        runUFC();
    }

    // MODIFIES: this
    // EFFECTS: interacts with user input
    private void runUFC() {
        boolean remainRunning = true;
        String command;

        initializeUFC();

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
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // EFFECTS: displays start menu with given options
    private void displayStartMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("-c-         Create my own fighter         -c-");
        System.out.println("-w-        Look at a weight class         -w-");
        System.out.println("-g-            Generate a fight           -g-");
        System.out.println("-r-        Generate a random fight        -r-");
        System.out.println("-q-                 Leave                 -q-");
    }

    // MODIFIES: this
    // EFFECTS: handles user input to generate a random fight between to fighters
    private void generateRandomFight() {
        System.out.println("\nPlease choose the weight class of the two fighters:");
        WeightClass selectedWeightClass = selectWeightClass();
        int size = selectedWeightClass.getFightersSize() - 1;
        int index = (int)Math.round(Math.random() * size);
        Fighter fighter = selectedWeightClass.getFighterOfIndex(index);

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
        Fighter fighterA = chooseFighter(selectedWeightClass);

        System.out.println("\nPlease choose your second fighter");
        Fighter fighterB = chooseFighter(selectedWeightClass);

        while (fighterA == fighterB) {
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
            System.out.println("Please input the fighter's name");
            System.out.println("If you wish to quit this window press q");
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
        Fighter fighter = chooseFighter(weightClass);

        System.out.println(fighter.getStats());

    }

    // EFFECTS: gets user to pick a fight by name and it will return a summary of the fight
    private void getSummaryFight(WeightClass weightClass) {
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
            System.out.println("All progress will be lost");
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
        double weight = 0.0;
        while (!(weightClass.getLowerWeightLimit() <= weight
            && weight <= weightClass.getUpperWeightLimit())) {
            if (!(weight == 0.0)) {
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
    private WeightClass selectWeightClass() {
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

    // EFFECTS: gets the corresponding weight class
    private WeightClass getCorrespondingWeightClass(String choice) {
        if (choice.equals("s")) {
            return strawWeight;
        } else if (choice.equals("f")) {
            return flyWeight;
        } else if (choice.equals("b")) {
            return bantamWeight;
        } else if (choice.equals("e")) {
            return featherWeight;
        } else if (choice.equals("l")) {
            return lightWeight;
        } else if (choice.equals("w")) {
            return welterWeight;
        } else if (choice.equals("m")) {
            return middleWeight;
        } else if (choice.equals("v")) {
            return lightHeavyWeight;
        } else {
            return heavyWeight;
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











    // MODIFIES: this
    // EFFECTS: initializes weight classes and fighters. Also adds fighters into their weight classes.
    private void initializeUFC() {
        userInput = new Scanner(System.in);

        initializeWeightClasses();
        initializeAllNames();
        initializeFighters();
    }

    // MODIFIES: this
    // EFFECTS: initializes weight classes
    private void initializeWeightClasses() {
        strawWeight = new WeightClass(0, 115, 0);
        flyWeight = new WeightClass(1, 115, 125);
        bantamWeight = new WeightClass(2, 125, 135);
        featherWeight = new WeightClass(3, 135, 145);
        lightWeight = new WeightClass(4, 145, 155);
        welterWeight = new WeightClass(5, 155, 170);
        middleWeight = new WeightClass(6, 170, 185);
        lightHeavyWeight = new WeightClass(7, 185, 205);
        heavyWeight = new WeightClass(8, 205, 265);
    }

    // EFFECTS: adds a bunch of names into 9 different list of names
    private void initializeAllNames() {
        initializeNames1();
        initializeNames2();
        initializeNames3();
        initializeNames4();
        initializeNames5();
        initializeNames6();
        initializeNames7();
        initializeNames8();
        initializeNames9();
    }

    // MODIFIES: this
    // EFFECTS: creates fighters and adds them to the 9 weight classes
    private void initializeFighters() {
        strawWeight.createFighters(names1);
        flyWeight.createFighters(names2);
        bantamWeight.createFighters(names3);
        featherWeight.createFighters(names4);
        lightWeight.createFighters(names5);
        welterWeight.createFighters(names6);
        middleWeight.createFighters(names7);
        lightHeavyWeight.createFighters(names8);
        heavyWeight.createFighters(names9);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames1() {
        names1 = new ArrayList<>();
        names1.add(NAME1);
        names1.add(NAME2);
        names1.add(NAME3);
        names1.add(NAME4);
        names1.add(NAME5);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames2() {
        names2 = new ArrayList<>();
        names2.add(NAME6);
        names2.add(NAME7);
        names2.add(NAME8);
        names2.add(NAME9);
        names2.add(NAME10);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames3() {
        names3 = new ArrayList<>();
        names3.add(NAME11);
        names3.add(NAME12);
        names3.add(NAME13);
        names3.add(NAME14);
        names3.add(NAME15);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames4() {
        names4 = new ArrayList<>();
        names4.add(NAME16);
        names4.add(NAME17);
        names4.add(NAME18);
        names4.add(NAME19);
        names4.add(NAME20);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames5() {
        names5 = new ArrayList<>();
        names5.add(NAME21);
        names5.add(NAME22);
        names5.add(NAME23);
        names5.add(NAME24);
        names5.add(NAME25);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames6() {
        names6 = new ArrayList<>();
        names6.add(NAME26);
        names6.add(NAME27);
        names6.add(NAME28);
        names6.add(NAME29);
        names6.add(NAME30);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames7() {
        names7 = new ArrayList<>();
        names7.add(NAME31);
        names7.add(NAME32);
        names7.add(NAME33);
        names7.add(NAME34);
        names7.add(NAME35);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames8() {
        names8 = new ArrayList<>();
        names8.add(NAME36);
        names8.add(NAME37);
        names8.add(NAME38);
        names8.add(NAME39);
        names8.add(NAME40);
    }

    // MODIFIES: this
    // EFFECTS: initializes names
    private void initializeNames9() {
        names9 = new ArrayList<>();
        names9.add(NAME41);
        names9.add(NAME42);
        names9.add(NAME43);
        names9.add(NAME44);
        names9.add(NAME45);
    }
}
