package ui;

import model.WeightClass;

import java.util.ArrayList;
import java.util.Scanner;

// Console based UI inspired by the tellerApp
public class UfcApp {
    private Scanner userInput;
    private boolean remainRunning;
    private int command;

    private WeightClass strawWeight;
    private WeightClass flyWeight;
    private WeightClass bantamWeight;
    private WeightClass featherWeight;
    private WeightClass lightWeight;
    private WeightClass welterWeight;
    private WeightClass middleWeight;
    private WeightClass lightHeavyWeight;
    private WeightClass heavyWeight;

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

        remainRunning = true;

        initializeUFC();

        while (remainRunning) {
            displayStartMenu();
            command = userInput.nextInt();


            if (command == 4) {
                leaveWindow();
            }
        }
    }

    private void leaveWindow() {
        System.out.println("\n Are you sure you want to leave?"
                + "\n all progress will be lost");
        System.out.println("\n -1- Yes -1-");
        System.out.println("-2- No -2-");
        if (command == 1) {
            remainRunning = false;
        } else if (command == 2) {
            remainRunning = true;

        } else {
            System.out.println("Invalid command");
        }
    }

    private void displayStartMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("-1- Create your own player -1-");
        System.out.println("-2- Look at a weight class -2-");
        System.out.println("-3-   Look at my fighter   -3-");
        System.out.println("-4-          Leave         -4-");
        // IF YOU LEAVE DO A WARNING YOUR DATA WILL BE LOST
    }


    // EFFECTS: initializes weight classes and fighters. Also adds fighters into their weight classes.
    private void initializeUFC() {
        userInput = new Scanner(System.in);

        initializeWeightClasses();
        initializeAllNames();
        initializeFighters();
    }

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

    private ArrayList<String> names1;
    private ArrayList<String> names2;
    private ArrayList<String> names3;
    private ArrayList<String> names4;
    private ArrayList<String> names5;
    private ArrayList<String> names6;
    private ArrayList<String> names7;
    private ArrayList<String> names8;
    private ArrayList<String> names9;

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

    // EFFECTS: initializes names
    private void initializeNames1() {
        names1 = new ArrayList<>();
        names1.add(NAME1);
        names1.add(NAME2);
        names1.add(NAME3);
        names1.add(NAME4);
        names1.add(NAME5);
    }

    // EFFECTS: initializes names
    private void initializeNames2() {
        names2 = new ArrayList<>();
        names2.add(NAME6);
        names2.add(NAME7);
        names2.add(NAME8);
        names2.add(NAME9);
        names2.add(NAME10);
    }

    // EFFECTS: initializes names
    private void initializeNames3() {
        names3 = new ArrayList<>();
        names3.add(NAME11);
        names3.add(NAME12);
        names3.add(NAME13);
        names3.add(NAME14);
        names3.add(NAME15);
    }

    // EFFECTS: initializes names
    private void initializeNames4() {
        names4 = new ArrayList<>();
        names4.add(NAME16);
        names4.add(NAME17);
        names4.add(NAME18);
        names4.add(NAME19);
        names4.add(NAME20);
    }

    // EFFECTS: initializes names
    private void initializeNames5() {
        names5 = new ArrayList<>();
        names5.add(NAME21);
        names5.add(NAME22);
        names5.add(NAME23);
        names5.add(NAME24);
        names5.add(NAME25);
    }

    // EFFECTS: initializes names
    private void initializeNames6() {
        names6 = new ArrayList<>();
        names6.add(NAME26);
        names6.add(NAME27);
        names6.add(NAME28);
        names6.add(NAME29);
        names6.add(NAME30);
    }

    // EFFECTS: initializes names
    private void initializeNames7() {
        names7 = new ArrayList<>();
        names7.add(NAME31);
        names7.add(NAME32);
        names7.add(NAME33);
        names7.add(NAME34);
        names7.add(NAME35);
    }

    // EFFECTS: initializes names
    private void initializeNames8() {
        names8 = new ArrayList<>();
        names8.add(NAME36);
        names8.add(NAME37);
        names8.add(NAME38);
        names8.add(NAME39);
        names8.add(NAME40);
    }

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
