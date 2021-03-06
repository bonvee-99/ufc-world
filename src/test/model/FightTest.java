package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the Fight class
public class FightTest {
    private Fighter fighter1;
    private Fighter fighter2;
    private Fight fight1;
    private Fight oldFight;

    @BeforeEach
    public void runBefore() {
        fighter1 = new Fighter("Ben Vinnick",
                "southpaw",
                145.0, 69, 21, 70);
        fighter2 = new Fighter("John John",
                "orthodox",
                144.0, 72, 25, 72);
        fight1 = new Fight(fighter1, fighter2, "first fight!");
        oldFight = new Fight(fighter1, fighter2, "old fight!",
                "Ben Vinnick beat John John by knockout!");
    }

    @Test
    public void constructorRandomResultTest() {
        chooseWinnerTest();
        chooseResultTest();
        assertEquals("first fight!", fight1.getFightName());
    }

    @Test
    public void constructorOldFightTest() {
        assertEquals("Ben Vinnick", oldFight.getWinner().getName());
        assertEquals("John John", oldFight.getLoser().getName());
        assertEquals("old fight!", oldFight.getFightName());
        assertEquals("Ben Vinnick beat John John by knockout!", oldFight.getResult());
    }


    @Test
    public void chooseWinnerTest() {
        Boolean validWinnerAndLoser;
        if ((fight1.getWinner().getName().equals(fighter1.getName())
                && fight1.getLoser().getName().equals(fighter2.getName()))
                || (fight1.getWinner().getName().equals(fighter2.getName())
                && fight1.getLoser().getName().equals(fighter1.getName()))) {
            validWinnerAndLoser = true;
        } else {
            validWinnerAndLoser = false;
        }
        assertTrue(validWinnerAndLoser);
    }


    @Test
    public void chooseResultTest() {
        fight1 = new Fight(fighter1, fighter2, "first fight!");
        String result = fight1.getResult();
        boolean isValidResult;
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
    public void getSummaryTest() {
        String summary = fight1.getSummary();
        boolean validResult;
        validResult = ((summary.contains(fighter2.getName()) && summary.contains(fighter1.getName()) && summary.contains("beat")) &&
                (summary.contains(" by knockout!") || summary.contains(" by technical knockout!")
                        || summary.contains(" by judges decision!")));
        assertTrue(validResult);
    }
}
