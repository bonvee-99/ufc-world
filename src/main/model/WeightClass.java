package model;

import java.util.HashSet;
import java.util.List;


// Represents a weight class in the UFC
public class WeightClass {
    private int weightClass;
    private HashSet<Fighter> fighters;
    private List<Fight> matchHistory;

    // Effects: creates a weight class with a list of fighters
    public WeightClass(int weightClass) {
        this.weightClass = weightClass;
        fighters = new HashSet<Fighter>();
    }

    // MODIFIES: WeightClass and Fighter given and random Fighter
    // EFFECTS: generates random opponent for fighter to fight, and then returns the results
    public String generateNextFight(Fighter fighter) {
        return null;
    }

    // MODIFIES: this
    // REQUIRED: adds a fighter to the list of fighters in the weight class
    public void addFighter(Fighter fighter) {
        this.fighters.add(fighter);
    }







    // Getters:

    public int getSize() {
        return fighters.size();
    }

    public int getWeightClass() {
        return this.weightClass;
    }

    public HashSet<Fighter> getFighters() {
        return this.fighters;
    }

    // Setters:

    public void setWeightClass(int weightClass) {
        this.weightClass = weightClass;
    }

    // MODIFIES: this
    // EFFECTS: removes all the fighters from the weight class
    public void clearFighters() {
        fighters.clear();
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
    // MODIFIES: this
    // EFFECTS: generates the fighters next opponent chooses winner based on odds, then adds to list of previous fights
    public String getNextFight() {
        return null;
    }





}



