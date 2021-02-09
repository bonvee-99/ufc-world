package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WeightClassTest {
    private WeightClass lightWeight;
    private Fighter fighter1;
    private Fighter fighter2;
    private Fighter fighter3;
    private String fight1;
    private String fight2;

    @BeforeEach
    public void runBefore() {
        lightWeight = new WeightClass(4, 145, 135);
        fighter1 = new Fighter("Ben Vinnick",
                "southpaw",
                145, 69, 19, 70);
        fighter2 = new Fighter("John John",
                "regular",
                144, 72, 25, 72);
        fighter3 = new Fighter("Sam Ham",
                "regular",
                144, 70, 30, 68);
        fight1 = "Ben Vinnick VS John John";
        fight2 = "John John VS Ben Vinnick";
    }

    @Test
    public void constructorTest() {
        assertEquals(4, lightWeight.getWeightClass());
        assertEquals(new ArrayList<Fighter>(), lightWeight.getFighters());
        assertEquals(new ArrayList<String>(), lightWeight.getMatchHistory());
        assertEquals(145, lightWeight.getUpperWeightLimit());
        assertEquals(135, lightWeight.getLowerWeightLimit());
    }

    @Test
    public void addFighterTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        lightWeight.addFighter(fighter3);
        assertEquals(3, lightWeight.getFightersSize());
        assertEquals(fighter1, lightWeight.getFighterByName("Ben Vinnick"));
        assertEquals(fighter2, lightWeight.getFighterByName("John John"));
    }

    @Test
    public void addFightTest() {
        lightWeight.addFight(fight1);
        lightWeight.addFight(fight2);
        assertEquals(2, lightWeight.getMatchHistorySize());
        assertEquals(fight1, lightWeight.getFightOfIndex(0));
        assertEquals(fight2, lightWeight.getFightOfIndex(1));
    }

    @Test
    public void listFightersTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        assertEquals("\nFighters:\nBen Vinnick" + "\nJohn John", lightWeight.listFighters());
    }

    @Test
    public void chooseOpponentTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        lightWeight.addFighter(fighter3);
        boolean notSameFighter;
        Fighter opponent = lightWeight.chooseOpponent(fighter1);
        if (opponent.getName().equals(fighter1.getName())) {
            notSameFighter = false;
        } else {
            notSameFighter = true;
        }
        assertTrue(notSameFighter);
    }

    @Test
    public void chooseWinnerTest() {
        Fighter winner = lightWeight.chooseWinner(fighter1, fighter2);
        //
    }

    @Test
    public void chooseResultTest() {
        boolean isValidResult;
        String result = lightWeight.chooseResult();
        if (result.equals(" by knockout!")) {
            isValidResult = true;
        } else if (result.equals(" by technical knockout!")) {
            isValidResult = true;
        } else if (result.equals(" by judges decision!")) {
            isValidResult = true;
        } else {
            isValidResult = false;
        }
        assertTrue(isValidResult);
    }

    @Test
    public void getNextFightTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        lightWeight.addFighter(fighter3);
        String result = lightWeight.getNextFight(fighter2);

        boolean validResult;

        if ((result.contains("John John") && result.contains("Ben Vinnick")
                || result.contains("Sam Ham")) && result.contains("beat") &&
                (result.contains(" by knockout!") || result.contains(" by technical knockout!")
                        || result.contains(" by judges decision!")))
        {
            validResult = true;
        } else {
            validResult = false;
        }

        assertTrue(validResult);
    }

    @Test
    public void createFightersOnlyOneTest() {
        ArrayList<String> inputNames = new ArrayList<>();
        inputNames.add("Bob Dylan");
        lightWeight.createFighters(inputNames);
        assertEquals(1, lightWeight.getFightersSize());
        Fighter fighter = lightWeight.getFighterByName("Bob Dylan");
        boolean inRange = lightWeight.getLowerWeightLimit()
                <= fighter.getWeight()
                && fighter.getWeight()
                <= lightWeight.getUpperWeightLimit();
        assertTrue(inRange);
    }

    @Test
    public void createFightersMultipleTest() {
        ArrayList<String> inputNames = new ArrayList<>();
        inputNames.add("Bob Dylan");
        inputNames.add("Jim Jones");
        inputNames.add("Chad Thad");
        lightWeight.createFighters(inputNames);
        assertEquals(3, lightWeight.getFightersSize());
        Fighter fighter = lightWeight.getFighterByName("Bob Dylan");
        boolean inRange = lightWeight.getLowerWeightLimit()
                <= fighter.getWeight()
                && fighter.getWeight()
                <= lightWeight.getUpperWeightLimit();
        assertTrue(inRange);





//TODO: replace all UfcApp fight creation initiates with this method!!!. make sure you keep copy of old one in case it doesn't work!!!

    //TODO: reference from stackoverflow on the range??!?!?!


}
