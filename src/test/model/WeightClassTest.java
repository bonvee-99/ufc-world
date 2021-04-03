package model;

import exceptions.NoFighterFoundException;
import exceptions.NoOtherFighterException;
import exceptions.NotInWeightClassException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WeightClassTest {
    private WeightClass lightWeight;
    private Fighter fighter1;
    private Fighter fighter2;
    private Fighter fighter3;
    private Fight fight1;
    private Fight fight2;

    @BeforeEach
    public void runBefore() {
        lightWeight = new WeightClass(4, 135, 145);
        fighter1 = new Fighter("Ben Vinnick",
                "southpaw",
                145.0, 69, 21, 70);
        fighter2 = new Fighter("John John",
                "orthodox",
                144.0, 72, 25, 72);
        fighter3 = new Fighter("Sam Ham",
                "orthodox",
                144.0, 70, 30, 68);
        fight1 = new Fight(fighter1, fighter2, "first match!");
        fight2 = new Fight(fighter2, fighter1, "second match!");
    }

    @Test
    public void constructorTest() {
        assertEquals(4, lightWeight.getWeightClassCode());
        assertEquals(new ArrayList<Fighter>(), lightWeight.getFighters());
        assertEquals(new ArrayList<Fight>(), lightWeight.getMatchHistory());
        assertEquals(145, lightWeight.getUpperWeightLimit());
        assertEquals(135, lightWeight.getLowerWeightLimit());
    }

    @Test
    public void addValidFighterTest() {
        lightWeight.addFighter(fighter1);
        assertTrue(lightWeight.addFighter(fighter2));
        assertEquals(2, lightWeight.getFightersSize());
        assertEquals(fighter1, lightWeight.getFighterByName("Ben Vinnick"));
        assertEquals(fighter2, lightWeight.getFighterByName("John John"));
    }

    @Test
    public void addDuplicateFighterNameTest() {
        lightWeight.addFighter(fighter1);
        assertFalse(lightWeight.addFighter(fighter1));
        assertEquals(1, lightWeight.getFightersSize());
    }

    @Test
    public void addIncorrectWeightFighterTest() {
        assertFalse(lightWeight.addFighter(new Fighter("Dave",
                "orthodox",
                170.0, 72, 25, 72)));
        assertEquals(0, lightWeight.getFightersSize());
    }

    @Test
    public void addValidFightTest() {
        lightWeight.addFight(fight1);
        assertTrue(lightWeight.addFight(fight2));
        assertEquals(2, lightWeight.getMatchHistorySize());
        assertEquals(fight1, lightWeight.getFightOfIndex(0));
        assertEquals(fight2, lightWeight.getFightOfIndex(1));
    }

    @Test
    public void addDuplicateFightNameTest() {
        lightWeight.addFight(fight1);
        assertFalse(lightWeight.addFight(fight1));
        assertEquals(1, lightWeight.getMatchHistorySize());
    }

    @Test
    public void getFighterByNameNotThere() {
        assertEquals(null, lightWeight.getFighterByName("Bob"));
    }

    @Test
    public void getFighterByNameIsThere() {
        lightWeight.addFighter(fighter1);
        assertEquals(fighter1, lightWeight.getFighterByName("Ben Vinnick"));
    }

    @Test
    public void getFightByNameNotThere() {
        assertEquals(null, lightWeight.getFightByName("Bob"));
    }

    @Test
    public void getFightByNameIsThere() {
        lightWeight.addFight(fight1);
        assertEquals(fight1, lightWeight.getFightByName("first match!"));
    }

    @Test
    public void listFightersNoneTest() {
        assertEquals("\nFighters:", lightWeight.listFighters());
    }

    @Test
    public void listFightersTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        assertEquals("\nFighters:" + "\n" + fighter1.getName()
                + "\n" + fighter2.getName(), lightWeight.listFighters());
    }

    @Test
    public void listFightsNoneTest() {
        assertEquals("\nFights:", lightWeight.listFights());
    }

    @Test
    public void listFightsTest() {
        lightWeight.addFight(fight1);
        lightWeight.addFight(fight2);
        assertEquals("\nFights:"
                + "\n" + fight1.getFightName()
                + "\n" + fight2.getFightName(), lightWeight.listFights());
    }

    @Test
    public void listFightSummariesNoneTest() {
        assertEquals("\nFight Summaries:", lightWeight.listFightSummaries());
    }

    @Test
    public void listFightSummariesTest() {
        lightWeight.addFight(fight1);
        lightWeight.addFight(fight2);
        String fight1Summary = fight1.getSummary();
        String fight2Summary = fight2.getSummary();
        String summaries = "\nFight Summaries:" + "\n" + fight1Summary + "\n" + fight2Summary;
        assertEquals(summaries, lightWeight.listFightSummaries());
    }

    @Test
    public void chooseOpponentNoExceptionExpectedTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        lightWeight.addFighter(fighter3);
        boolean notSameFighter;
        try {
            Fighter opponent = lightWeight.chooseOpponent(fighter1);
            notSameFighter = !opponent.getName().equals(fighter1.getName());
            assertTrue(notSameFighter);
        } catch (NoOtherFighterException | NotInWeightClassException e) {
            fail("unexpected exception");
        }
    }

    @Test
    public void chooseOpponentNotInClassExceptionExpectedTest() {
        lightWeight.addFighter(fighter2);
        lightWeight.addFighter(fighter3);
        try {
            Fighter opponent = lightWeight.chooseOpponent(fighter1);
            fail("expected exception");
        } catch (NotInWeightClassException e) {
            // should reach here
        } catch (NoOtherFighterException e) {
           fail("unexpected exception");
        }
    }

    @Test
    public void chooseOpponentNoOtherFighterExceptionExpectedTest() {
        lightWeight.addFighter(fighter2);
        try {
            Fighter opponent = lightWeight.chooseOpponent(fighter2);
            fail("expected exception");
        } catch (NotInWeightClassException e) {
            fail("unexpected exception");
        } catch (NoOtherFighterException e) {
            // should reach
        }
    }

    @Test
    public void chooseOpponentBothExceptionsThrownTest() {
        lightWeight.addFighter(fighter2);
        try {
            Fighter opponent = lightWeight.chooseOpponent(fighter1);
            fail("expected exception");
        } catch (NotInWeightClassException e) {
            fail("unexpected exception");
        } catch (NoOtherFighterException e) {
            // should reach
        }
    }

    @Test
    public void getRandomFighterFromOneNoExceptionTest() {
        lightWeight.addFighter(fighter1);
        try {
            assertEquals(fighter1, lightWeight.getRandomFighter());
        } catch (NoFighterFoundException e) {
            fail("unexpected exception");
        }
    }

    @Test
    public void getRandomFighterFromMultipleNoExceptionTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        try {
            Fighter fighter = lightWeight.getRandomFighter();
            boolean isValid = fighter == fighter1
                    || fighter == fighter2;
            assertTrue(isValid);
        } catch (NoFighterFoundException e) {
            fail("unexpected exception");
        }
    }

    @Test
    public void getRandomFighterExceptionExpectedTest() {
        try {
            Fighter fighter = lightWeight.getRandomFighter();
            fail("expected exception");
        } catch (NoFighterFoundException e) {
            // should reach
        }
    }

    @Test
    public void getNextFightValidTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        Fight fight = lightWeight.getNextFight(fighter1, fighter2, "test fight!");
        assertTrue(!(fight == null));
        assertEquals(fight, lightWeight.getFightByName("test fight!"));
        assertEquals(1, fight.getWinner().getWins());
        assertEquals(0, fight.getLoser().getWins());
    }

    @Test
    public void getNextFightSameFightersTest() {
        lightWeight.addFighter(fighter1);
        assertTrue(lightWeight.getNextFight(fighter1, fighter1, "test") == null);
    }

    @Test
    public void getNextFightNotInWeightClassTest() {
        lightWeight.addFighter(fighter1);
        assertTrue(lightWeight.getNextFight(fighter1, fighter2, "test") == null);
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
    }
}
