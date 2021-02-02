package model;

import java.util.HashSet;


// Represents a weight class in the UFC
public class WeightClass {
    private String weightClass;
    private HashSet<Fighter> fighters;

    // Effects: creates a weight class with a list of fighters
    public WeightClass(String weightClass) {
        this.weightClass = weightClass;
        fighters = new HashSet<Fighter>();
    }

    // Getters:

    public String getWeightClass() {
        return this.weightClass;
    }

    public HashSet<Fighter> getFighters() {
        return this.fighters;
    }

    // Setters:

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    // MODIFIES: this
    // EFFECTS: removes all the fighters from the weight class
    public void clearFighters() {
        fighters.clear();
    }

    // MODIFIES: this
    // REQUIRED: adds a fighter to the list of fighters in the weight class
    public void addFighter(Fighter fighter) {
        this.fighters.add(fighter);
    }

    // EFFECTS: prints the list of fighters in the weight class
    public void printFighters() {
        System.out.println("fighters:");
        for (Fighter fighter : fighters) {
            System.out.print(fighter.getName());
        }
    }

    // REQUIRES: at least one fighter in the weight class
    // EFFECTS: prints the best fighter in the weight class based on win percentage and matches
    public String getBestFighter() {
        return null;
    }

    // REQUIRES: at least one other fighter in the weight class
    // EFFECTS: generates the fighters next opponent
    public String getNextFight() {
        return null;
    }



}

//TODO: do we need requires/etc for getters/setters. What is considered a getter/setter?

/* weight classes:
flyweight = 1
bantamweight = 2
featherweight = 3
lightweight = 4
welterweight = 5
middleweight = 6
light heavyweight = 7
heavyweight = 8

women's strawweight = 10
women's flyweight = 11
women's bantamweight = 12
women's featherweight = 13
 */