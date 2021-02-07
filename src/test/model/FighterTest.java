package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.UFC;


import static org.junit.jupiter.api.Assertions.*;

// Tests for the Fight class
class FighterTest {
    private Fighter fighter1;
    private Fighter fighter2;
    private Fighter fighter3;
    private Fighter fighter4;
    private Fighter fighter5;
    private Fighter fighter6;
    private Fighter fighter7;

    @BeforeEach
    public void runBefore() {
        fighter1 = new Fighter("Ben Vinnick",
                "southpaw",
                145, 69, 19, 70);
        fighter2 = new Fighter("John John",
                "regular",
                156, 72, 25, 72);
        fighter3 = new Fighter("Big Man Dan",
                "regular",
                266, 70, 35, 65);
        fighter4 = new Fighter("Small Man Cam",
                "regular",
                100, 73, 27, 75);
        fighter5 = new Fighter("random Man Sam", 160, 1);
        fighter6 = new Fighter("random Man Bob", 160, 1);
        fighter7 = new Fighter("random man Dave", 160, 0);

        WeightClass strawWeight = new WeightClass(0);
        WeightClass flyWeight = new WeightClass(1);
        WeightClass bantamWeight = new WeightClass(2);
        WeightClass featherWeight = new WeightClass(3);
        WeightClass lightWeight = new WeightClass(4);
        WeightClass welterWeight = new WeightClass(5);
        WeightClass middleWeight = new WeightClass(6);
        WeightClass lightHeavyWeight = new WeightClass(7);
        WeightClass heavyWeight = new WeightClass(8);
    }

    @Test
    public void mainConstructorTest() {
        assertEquals("Ben Vinnick", fighter1.getName());
        assertEquals("southpaw", fighter1.getStance());
        assertEquals(145, fighter1.getWeight());
        assertEquals(69, fighter1.getHeight());
        assertEquals(19, fighter1.getAge());
        assertEquals(70, fighter1.getReach());
        assertEquals(0, fighter1.getWins());
        assertEquals(0, fighter1.getLosses());
        assertEquals(3, fighter1.getWeightClass());
    }

    @Test
    public void randomConstructorTest() {
        assertEquals("random man Sam", fighter5.getName());
        assertEquals(160, fighter5.getWeight());
        boolean setStanceProperly;
        if (fighter5.getStance() == "regular" || fighter5.getStance() == "southpaw") {
            setStanceProperly = true;
        } else {
            setStanceProperly = false;
        }
        boolean setHeightProperly;
        if (60 < fighter5.getHeight() && fighter5.getHeight() < 76) {
            setHeightProperly = true;
        } else {
            setHeightProperly = false;
        }

        this.age = (int)Math.round(Math.random() * (45 - 19) + 19);
        this.reach = (int)Math.round(Math.random() * (76 - 60) + 60);
        this.wins = (int)Math.round(Math.random() * (30 - 15) + 15);
        this.losses = (int)Math.round(Math.random() * (10 - 0) + 0);
    }


    @Test
    public void assignWeightClassWithinTest() {
        fighter1.assignWeightClass();
        assertEquals(3, fighter1.getWeightClass());
    }

    @Test
    public void assignWeightClassOnEdgeTest() {
        fighter2.assignWeightClass();
        assertEquals(5, fighter2.getWeightClass());
    }

    @Test
    public void assignWeightClassSuperTest() {
        fighter3.assignWeightClass();
        assertEquals(8, fighter3.getWeightClass());
    }

    @Test
    public void assignWeightClassStrawTest() {
        fighter4.assignWeightClass();
        assertEquals(0, fighter4.getWeightClass());
    }

    @Test
    public void getWinPercentageNoMatchesTest() {
        assertEquals(0,fighter4.getWinPercentage());
    }

    @Test
    public void getWinPercentageHasMatches() {
        boolean notZero;
        if (fighter5.getWinPercentage() == 0) {
            notZero = false;
        } else {
            notZero = true;
        }
        assertTrue(notZero);
    }

    @Test
    public void getStatsTest() {
        String stats = "Stance: " + "southpaw"
                + "\nWeight: " + 145
                + "\nHeight: " + 69
                + "\nAge: " + 19
                + "\nReach: " + 70
                + "\nWins: " + 0
                + "\nLosses: " + 0
                + "\nWin percentage: " + "0";
        assertEquals(stats, fighter1.getStats());
    }











}