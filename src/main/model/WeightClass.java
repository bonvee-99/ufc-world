package model;

import java.util.ArrayList;
import java.util.List;

// Represents a weight class in the UFC
public class WeightClass {
    private int weightClass;
    private ArrayList<Fighter> fighters;

    // Effects: creates a weight class
    public WeightClass(int weightNum) {
        this.weightClass = weightNum;
        fighters = new ArrayList<Fighter>();
    }

    public void addFighter(Fighter name) {
        this.fighters.add(name);
    }

    public void printFighters() {
        System.out.println("Weightclass' fighters:");
        for (Fighter fighter : fighters) {
            System.out.println(fighter.getName());
        }

    }


}

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