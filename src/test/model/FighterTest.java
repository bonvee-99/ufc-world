package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

// Tests for the Fight class
class FighterTest {
    private Fighter fighter1;
    private Fighter fighter4;
    private Fighter fighterGoodRandomA;
    private Fighter fighterBadRandom;

    @BeforeEach
    public void runBefore() {
        fighter1 = new Fighter("Ben Vinnick",
                "southpaw",
                145.0, 69, 21, 70);
        fighter4 = new Fighter("Small Man Cam",
                "orthodox",
                100.0, 73, 27, 75);
        fighterGoodRandomA = new Fighter("random Man Sam", 160.0, 1);
        fighterBadRandom = new Fighter("random Man Dave", 160.0, 0);
    }

    @Test
    public void mainConstructorTest() {
        assertEquals("Ben Vinnick", fighter1.getName());
        assertEquals("southpaw", fighter1.getStance());
        assertEquals(145.0, fighter1.getWeight());
        assertEquals(69, fighter1.getHeight());
        assertEquals(21, fighter1.getAge());
        assertEquals(70, fighter1.getReach());
        assertEquals(0, fighter1.getWins());
        assertEquals(0, fighter1.getLosses());
    }

    @Test
    public void randomGoodFighterConstructorTest() {
        assertEquals("random Man Sam", fighterGoodRandomA.getName());
        assertEquals(160.0, fighterGoodRandomA.getWeight());

        boolean setStanceProperly = (fighterGoodRandomA.getStance().equals("orthodox")
                || fighterGoodRandomA.getStance().equals("southpaw"));

        boolean setHeightProperly = (60 <= fighterGoodRandomA.getHeight() && fighterGoodRandomA.getHeight() <= 76);

        boolean setAgeProperly = (21 <= fighterGoodRandomA.getAge() && fighterGoodRandomA.getAge() <= 34);

        boolean setReachProperly = (60 <= fighterGoodRandomA.getReach() && fighterGoodRandomA.getReach() <= 80);

        boolean setWinsProperly = (15 <= fighterGoodRandomA.getWins() && fighterGoodRandomA.getWins() <= 30);

        boolean setLossesProperly = (0 <= fighterGoodRandomA.getLosses() && fighterGoodRandomA.getLosses() <= 10);

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
        assertEquals(160.0, fighterBadRandom.getWeight());

        boolean setStanceProperly = (fighterBadRandom.getStance().equals("orthodox")
                || fighterBadRandom.getStance().equals("southpaw"));

        boolean setHeightProperly = (60 <= fighterBadRandom.getHeight() && fighterBadRandom.getHeight() <= 76);

        boolean setAgeProperly = (21 <= fighterBadRandom.getAge() && fighterBadRandom.getAge() <= 34);

        boolean setReachProperly = (60 <= fighterBadRandom.getReach() && fighterBadRandom.getReach() <= 76);

        boolean setWinsProperly = (0 <= fighterBadRandom.getWins() && fighterBadRandom.getWins() <= 10);

        boolean setLossesProperly = (10 <= fighterBadRandom.getLosses() && fighterBadRandom.getLosses() <= 20);

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
        boolean notZero = !(fighterGoodRandomA.getWinPercentage() == 0);
        assertTrue(notZero);
    }

    @Test
    public void getStatsTest() {
        String stats =
                "\n" + fighter1.getName() + "'s stats:"
                        + "\nStance: " + "southpaw"
                        + "\nWeight: " + 145.0 + "lbs"
                        + "\nHeight: " + 69 + "in"
                        + "\nReach: " + 70
                        + "\nAge: " + 21
                        + "\nWins: " + 0
                        + "\nLosses: " + 0
                        + "\nWin percentage: " + "0";
        assertEquals(stats, fighter1.getStats());
    }

    @Test
    public void createFightersTest() {

    }
}
