package ui;


import model.Fighter;
import model.WeightClass;

public class Main {
    public static void main(String[] args) {
        WeightClass lightweight = new WeightClass(4);
        Fighter benVinnick = new Fighter("Ben Vinnick");
        Fighter jonathanHaggerty = new Fighter("Jonathan Haggerty");
        lightweight.addFighter(benVinnick);
        lightweight.addFighter(jonathanHaggerty);
        lightweight.printFighters();
    }
}
