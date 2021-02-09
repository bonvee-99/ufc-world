package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

// Tests for the Fight class
class FighterTest {
    private Fighter fighter1;
    private Fighter fighter2;
    private Fighter fighter3;
    private Fighter fighter4;
    private Fighter fighterGoodRandomA;
    private Fighter fighterBadRandom;

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
        fighterGoodRandomA = new Fighter("random Man Sam", 160, 1);
        fighterBadRandom = new Fighter("random Man Dave", 160, 0);
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
    }

    @Test
    public void randomGoodFighterConstructorTest() {
        assertEquals("random Man Sam", fighterGoodRandomA.getName());
        assertEquals(160, fighterGoodRandomA.getWeight());

        boolean setStanceProperly;
        if (fighterGoodRandomA.getStance().equals("regular") || fighterGoodRandomA.getStance().equals("southpaw")) {
            setStanceProperly = true;
        } else {
            setStanceProperly = false;
        }
        boolean setHeightProperly;
        if (60 <= fighterGoodRandomA.getHeight() && fighterGoodRandomA.getHeight() <= 76) {
            setHeightProperly = true;
        } else {
            setHeightProperly = false;
        }

        boolean setAgeProperly;
        if (19 <= fighterGoodRandomA.getAge() && fighterGoodRandomA.getAge() <= 45) {
            setAgeProperly = true;
        } else {
            setAgeProperly = false;
        }

        boolean setReachProperly;
        if (60 <= fighterGoodRandomA.getReach() && fighterGoodRandomA.getReach() <= 80) {
            setReachProperly = true;
        } else {
            setReachProperly = false;
        }

        boolean setWinsProperly;
        if (15 <= fighterGoodRandomA.getWins() && fighterGoodRandomA.getWins() <= 30) {
            setWinsProperly = true;
        } else {
            setWinsProperly = false;
        }

        boolean setLossesProperly;
        if (0 <= fighterGoodRandomA.getLosses() && fighterGoodRandomA.getLosses() <= 10) {
            setLossesProperly = true;
        } else {
            setLossesProperly = false;
        }

        assertTrue(setStanceProperly);
        assertTrue(setHeightProperly);
        assertTrue(setAgeProperly);
        assertTrue(setReachProperly);
        assertTrue(setWinsProperly);
        assertTrue(setLossesProperly);
    }

    @Test
    public void randomBadFighterConstructorTest() {
        assertEquals("random Man Dave", fighterBadRandom.getName());
        assertEquals(160, fighterBadRandom.getWeight());

        boolean setStanceProperly;
        if (fighterBadRandom.getStance().equals("regular") || fighterBadRandom.getStance().equals("southpaw")) {
            setStanceProperly = true;
        } else {
            setStanceProperly = false;
        }
        boolean setHeightProperly;
        if (60 <= fighterBadRandom.getHeight() && fighterBadRandom.getHeight() <= 76) {
            setHeightProperly = true;
        } else {
            setHeightProperly = false;
        }

        boolean setAgeProperly;
        if (19 <= fighterBadRandom.getAge() && fighterBadRandom.getAge() <= 45) {
            setAgeProperly = true;
        } else {
            setAgeProperly = false;
        }

        boolean setReachProperly;
        if (60 <= fighterBadRandom.getReach() && fighterBadRandom.getReach() <= 76) {
            setReachProperly = true;
        } else {
            setReachProperly = false;
        }

        boolean setWinsProperly;
        if (0 <= fighterBadRandom.getWins() && fighterBadRandom.getWins() <= 10) {
            setWinsProperly = true;
        } else {
            setWinsProperly = false;
        }

        boolean setLossesProperly;
        if (10 <= fighterBadRandom.getLosses() && fighterBadRandom.getLosses() <= 20) {
            setLossesProperly = true;
        } else {
            setLossesProperly = false;
        }

        assertTrue(setStanceProperly);
        assertTrue(setHeightProperly);
        assertTrue(setAgeProperly);
        assertTrue(setReachProperly);
        assertTrue(setWinsProperly);
        assertTrue(setLossesProperly);
    }

    @Test
    public void getWinPercentageNoMatchesTest() {
        assertEquals(0, fighter4.getWinPercentage());
    }

    @Test
    public void getWinPercentageOnlyLossesTest() {
        assertEquals(0, fighter1.getLosses());
        fighter1.addLoss();
        assertEquals(0, fighter1.getWinPercentage());
    }

    @Test
    public void getWinPercentageOnlyWinsTest() {
        assertEquals(0, fighter1.getLosses());
        fighter1.addWin();
        assertEquals(100, fighter1.getWinPercentage());
    }

    @Test
    public void getWinPercentageHasMatches() {
        boolean notZero;
        if (fighterGoodRandomA.getWinPercentage() == 0) {
            notZero = false;
        } else {
            notZero = true;
        }
        assertTrue(notZero);
    }

    @Test
    public void getStatsTest() {
        String stats =
                "\n" + fighter1.getName() + "'s stats:"
                        + "\nStance: " + "southpaw"
                        + "\nWeight: " + 145
                        + "\nHeight: " + 69
                        + "\nAge: " + 19
                        + "\nReach: " + 70
                        + "\nWins: " + 0
                        + "\nLosses: " + 0
                        + "\nWin percentage: " + "0";
        assertEquals(stats, fighter1.getStats());
    }

    @Test
    public void createFightersTest() {

    }











}
