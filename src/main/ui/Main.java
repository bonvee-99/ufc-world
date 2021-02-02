package ui;


import model.Fighter;
import model.WeightClass;

public class Main {
    public static void main(String[] args) {
        WeightClass lightweight = new WeightClass("lightweight");
        Fighter benVinnick = new Fighter("Ben Vinnick",
                "southpaw",
                145, 69, 19, 70, 10, 1);
        Fighter randomFighter1 = new Fighter("John Doe", 170, 0);
        Fighter randomFighter2 = new Fighter("Damien Lillard", 150, 1);



    }
}
