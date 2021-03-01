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
    public void getRandomFighterFromOneTest() {
        lightWeight.addFighter(fighter1);
        assertEquals(fighter1, lightWeight.getRandomFighter());
    }

    @Test
    public void getRandomFighterFromMultipleTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        Fighter fighter = lightWeight.getRandomFighter();
        boolean isValid = fighter == fighter1
                || fighter == fighter2;
        assertTrue(isValid);
    }

    @Test
    public void getNextFightTest() {
        lightWeight.addFighter(fighter1);
        lightWeight.addFighter(fighter2);
        lightWeight.addFighter(fighter3);
        Fighter opponent = lightWeight.chooseOpponent(fighter1);

        Fight fight = lightWeight.getNextFight(fighter1, opponent, "test fight!");
        assertEquals(fight, lightWeight.getFightByName("test fight!"));
        assertEquals(1, fight.getWinner().getWins());
        assertEquals(0, fight.getLoser().getWins());
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
