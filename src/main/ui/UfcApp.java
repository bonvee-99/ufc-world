package ui;

import model.Fighter;
import model.WeightClass;

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

    private Fighter strawWeightFighter1;
    private Fighter strawWeightFighter2;
    private Fighter strawWeightFighter3;
    private Fighter strawWeightFighter4;
    private Fighter strawWeightFighter5;

    private Fighter flyWeightFighter1;
    private Fighter flyWeightFighter2;
    private Fighter flyWeightFighter3;
    private Fighter flyWeightFighter4;
    private Fighter flyWeightFighter5;

    private Fighter bantamWeightFighter1;
    private Fighter bantamWeightFighter2;
    private Fighter bantamWeightFighter3;
    private Fighter bantamWeightFighter4;
    private Fighter bantamWeightFighter5;

    private Fighter featherWeightFighter1;
    private Fighter featherWeightFighter2;
    private Fighter featherWeightFighter3;
    private Fighter featherWeightFighter4;
    private Fighter featherWeightFighter5;

    private Fighter lightWeightFighter1;
    private Fighter lightWeightFighter2;
    private Fighter lightWeightFighter3;
    private Fighter lightWeightFighter4;
    private Fighter lightWeightFighter5;

    private Fighter welterWeightFighter1;
    private Fighter welterWeightFighter2;
    private Fighter welterWeightFighter3;
    private Fighter welterWeightFighter4;
    private Fighter welterWeightFighter5;

    private Fighter middleWeightFighter1;
    private Fighter middleWeightFighter2;
    private Fighter middleWeightFighter3;
    private Fighter middleWeightFighter4;
    private Fighter middleWeightFighter5;

    private Fighter lightHeavyWeightFighter1;
    private Fighter lightHeavyWeightFighter2;
    private Fighter lightHeavyWeightFighter3;
    private Fighter lightHeavyWeightFighter4;
    private Fighter lightHeavyWeightFighter5;

    private Fighter heavyWeightFighter1;
    private Fighter heavyWeightFighter2;
    private Fighter heavyWeightFighter3;
    private Fighter heavyWeightFighter4;
    private Fighter heavyWeightFighter5;

    private Fighter myFighter;

    // EFFECTS: runs the UFC application
    public UfcApp() {
        runUFC();
    }


    // MODIFIES: this
    // EFFECTS: interacts with user input
    private void runUFC() {
        initializeUFC();
        
        remainRunning = true;

        
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

        initializeStrawWeightFighters();
        initializeFlyWeightFighters();
        initializeBantamWeightFighters();
        initializeFeatherWeightFighters();
        initializeLightWeightFighters();
        initializeWelterWeightFighters();
        initializeMiddleWeightFighters();
        initializeLightHeavyWeightFighters();
        initializeHeavyWeightFighters();
    }

    // EFFECTS: initializes weight classes
    private void initializeWeightClasses() {
        strawWeight = new WeightClass(0);
        flyWeight = new WeightClass(1);
        bantamWeight = new WeightClass(2);
        featherWeight = new WeightClass(3);
        lightWeight = new WeightClass(4);
        welterWeight = new WeightClass(5);
        middleWeight = new WeightClass(6);
        lightHeavyWeight = new WeightClass(7);
        heavyWeight = new WeightClass(8);
    }

    // EFFECTS: initializes strawweight fighters and adds them to the strawweight division
    private void initializeStrawWeightFighters() {
        strawWeightFighter1 = new Fighter("Tiny Tim", 100, 1);
        strawWeight.addFighter(strawWeightFighter1);
        strawWeightFighter2 = new Fighter("Joel Hammond", 115, 1);
        strawWeight.addFighter(strawWeightFighter2);
        strawWeightFighter3 = new Fighter("Rick Feder", 112, 1);
        strawWeight.addFighter(strawWeightFighter3);
        strawWeightFighter4 = new Fighter("Sam Davids", 109, 0);
        strawWeight.addFighter(strawWeightFighter4);
        strawWeightFighter5 = new Fighter("Tony Brooks", 114, 0);
        strawWeight.addFighter(strawWeightFighter5);
    }

    // EFFECTS: initializes flyweight fighters and adds them to the flyweight division
    private void initializeFlyWeightFighters() {
        flyWeightFighter1 = new Fighter("Connor Marsh", 125, 1);
        flyWeight.addFighter(flyWeightFighter1);
        flyWeightFighter2 = new Fighter("Sameer Frey", 124, 1);
        flyWeight.addFighter(flyWeightFighter2);
        flyWeightFighter3 = new Fighter("Antoni Cain", 120, 1);
        flyWeight.addFighter(flyWeightFighter3);
        flyWeightFighter4 = new Fighter("Kier Huber", 116, 0);
        flyWeight.addFighter(flyWeightFighter4);
        flyWeightFighter5 = new Fighter("Connor Marsh", 125, 0);
        flyWeight.addFighter(flyWeightFighter5);
    }

    // EFFECTS: initializes bantamweight fighters and adds them to the bantamweight division
    private void initializeBantamWeightFighters() {
        bantamWeightFighter1 = new Fighter("Kelsey Zuniga", 135, 1);
        bantamWeight.addFighter(bantamWeightFighter1);
        bantamWeightFighter2 = new Fighter("Osama Lindsey", 130, 1);
        bantamWeight.addFighter(bantamWeightFighter2);
        bantamWeightFighter3 = new Fighter("Domas Werner", 134, 1);
        bantamWeight.addFighter(bantamWeightFighter3);
        bantamWeightFighter4 = new Fighter("Raheel Cortez", 133, 0);
        bantamWeight.addFighter(bantamWeightFighter4);
        bantamWeightFighter5 = new Fighter("Kamran Oakley", 129, 0);
        bantamWeight.addFighter(bantamWeightFighter5);
    }

    // EFFECTS: initializes featherweight fighters and adds them to the featherweight division
    private void initializeFeatherWeightFighters() {
        featherWeightFighter1 = new Fighter("Ronny Russel", 145, 1);
        featherWeight.addFighter(featherWeightFighter1);
        featherWeightFighter2 = new Fighter("Tyrese Holcomb", 143, 1);
        featherWeight.addFighter(featherWeightFighter2);
        featherWeightFighter3 = new Fighter("Tyson Foley", 142, 1);
        featherWeight.addFighter(featherWeightFighter3);
        featherWeightFighter4 = new Fighter("Jasper Workman", 145, 0);
        featherWeight.addFighter(featherWeightFighter4);
        featherWeightFighter5 = new Fighter("Joss Daniel", 140, 0);
        featherWeight.addFighter(featherWeightFighter5);
    }

    // EFFECTS: initializes lightweight fighters and adds them to the lightweight division
    private void initializeLightWeightFighters() {
        lightWeightFighter1 = new Fighter("Wil Wilkes", 155, 1);
        lightWeight.addFighter(lightWeightFighter1);
        lightWeightFighter2 = new Fighter("Yusef Jenkins", 153, 1);
        lightWeight.addFighter(lightWeightFighter2);
        lightWeightFighter3 = new Fighter("Caine Webber", 154, 1);
        lightWeight.addFighter(lightWeightFighter3);
        lightWeightFighter4 = new Fighter("Shannon Cornish", 150, 0);
        lightWeight.addFighter(lightWeightFighter4);
        lightWeightFighter5 = new Fighter("Carl Rodriquez", 149, 0);
        lightWeight.addFighter(lightWeightFighter5);
    }

    // EFFECTS: initializes welterweight fighters and adds them to the welterweight division
    private void initializeWelterWeightFighters() {
        welterWeightFighter1 = new Fighter("Denzel Hendricks", 170, 1);
        welterWeight.addFighter((welterWeightFighter1));
        welterWeightFighter2 = new Fighter("Patryk Wade", 169, 1);
        welterWeight.addFighter((welterWeightFighter2));
        welterWeightFighter3 = new Fighter("Cayden Mcnamara", 170, 1);
        welterWeight.addFighter((welterWeightFighter3));
        welterWeightFighter4 = new Fighter("Macaulay Bowes", 162, 0);
        welterWeight.addFighter((welterWeightFighter4));
        welterWeightFighter5 = new Fighter("Maverick Bush", 160, 0);
        welterWeight.addFighter((welterWeightFighter5));
    }

    // EFFECTS: initializes middleweight fighters and adds them to the middleweight division
    private void initializeMiddleWeightFighters() {
        middleWeightFighter1 = new Fighter("Kohen Walker", 185, 1);
        middleWeight.addFighter(middleWeightFighter1);
        middleWeightFighter2 = new Fighter("Sahil Ortega", 184, 1);
        middleWeight.addFighter(middleWeightFighter2);
        middleWeightFighter3 = new Fighter("Dominic Potter", 185, 1);
        middleWeight.addFighter(middleWeightFighter3);
        middleWeightFighter4 = new Fighter("Imani Nicholls", 176, 0);
        middleWeight.addFighter(middleWeightFighter4);
        middleWeightFighter5 = new Fighter("Ronaldo Gallegos", 179, 0);
        middleWeight.addFighter(middleWeightFighter5);
    }

    // EFFECTS: initializes light heavyweight fighters and adds them to the light heavyweight division
    private void initializeLightHeavyWeightFighters() {
        lightHeavyWeightFighter1 = new Fighter("Lawson Russo", 205, 1);
        lightHeavyWeight.addFighter(lightHeavyWeightFighter1);
        lightHeavyWeightFighter2 = new Fighter("Gerard Tang", 200, 1);
        lightHeavyWeight.addFighter(lightHeavyWeightFighter2);
        lightHeavyWeightFighter3 = new Fighter("Matthias Grant", 204, 1);
        lightHeavyWeight.addFighter(lightHeavyWeightFighter3);
        lightHeavyWeightFighter4 = new Fighter("Hunter Mann", 186, 0);
        lightHeavyWeight.addFighter(lightHeavyWeightFighter4);
        lightHeavyWeightFighter5 = new Fighter("Charles Petty", 199, 1);
        lightHeavyWeight.addFighter(lightHeavyWeightFighter5);
    }

    // EFFECTS: initializes heavyweight fighters and adds them to the heavyweight division
    private void initializeHeavyWeightFighters() {
        heavyWeightFighter1 = new Fighter("Lucien Sharp", 265, 1);
        heavyWeight.addFighter(heavyWeightFighter1);
        heavyWeightFighter2 = new Fighter("Saad Redmond", 260, 1);
        heavyWeight.addFighter(heavyWeightFighter2);
        heavyWeightFighter3 = new Fighter("Kristopher Mcneill", 215, 1);
        heavyWeight.addFighter(heavyWeightFighter3);
        heavyWeightFighter4 = new Fighter("Dainton Carson", 210, 0);
        heavyWeight.addFighter(heavyWeightFighter4);
        heavyWeightFighter5 = new Fighter("Emmett Esparza", 209, 0);
        heavyWeight.addFighter(heavyWeightFighter5);
    }

}




    //TODO: can enter a weight class and do the following:
    //TODO: can add a new player. Console asks you to fill in their stats.
    //TODO: can generate a players next fight
    //TODO: can view all the recent fights and the winner
    //TODO: randomize the winner of 2 fights. Adds it to history



    //TODO: TEST BASED DESIGN!!!


