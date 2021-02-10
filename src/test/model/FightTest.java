package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the Fight class
public class FightTest {
    private Fighter fighter1;
    private Fighter fighter2;
    private Fight fight1;

    @BeforeEach
    public void runBefore() {
        fighter1 = new Fighter("Ben Vinnick",
                "southpaw",
                145, 69, 19, 70);
        fighter2 = new Fighter("John John",
                "regular",
                144, 72, 25, 72);
        fight1 = new Fight(fighter1, fighter2, "first fight!");
    }

    @Test
    public void constructorTest() {
        chooseWinnerTest();
        chooseResultTest();
        assertEquals("first fight!", fight1.getFightName());
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
        if ((summary.contains(fighter2.getName()) && summary.contains(fighter1.getName()) && summary.contains("beat")) &&
                (summary.contains(" by knockout!") || summary.contains(" by technical knockout!")
                        || summary.contains(" by judges decision!"))) {
            validResult = true;
        } else {
            validResult = false;
        }
        assertTrue(validResult);
    }
}
