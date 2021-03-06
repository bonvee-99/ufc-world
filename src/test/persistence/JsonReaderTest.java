package persistence;

import model.Fight;
import model.Fighter;
import model.UfcWorld;
import model.WeightClass;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Tests inspired by JsonSerializationDemo tests
public class JsonReaderTest {

    @Test
    public void noFileFoundTest() {
        JsonReader jsonReader = new JsonReader(".data/FileNotThere.json");
        try {
            UfcWorld myWorld = jsonReader.read();
            fail("expected IOException");
        } catch (IOException e) {
            // code is working
        }
    }

    @Test
    public void readerEmptyUfcWorldFoundTest() {
        JsonReader reader = new JsonReader("./data/emptyUfcWorldFoundTest.json");
        try {
            UfcWorld myWorld = reader.read();
            assertEquals("empty UFC world", myWorld.getName());
            assertEquals(0, myWorld.getWeightClassListSize());
        } catch (IOException e) {
            fail("Error reading from given file");
        }
    }

    @Test
    public void readerFullUfcWorldFoundTest() {
        JsonReader reader = new JsonReader("./data/fullUfcWorldFoundTest.json");
        try {
            UfcWorld myWorld = reader.read();
            readValidWorldTest(myWorld);
        } catch (IOException e) {
            fail("Error reading from given file");
        }
    }

    private void readValidWorldTest(UfcWorld myWorld) {
        assertEquals("full UFC world", myWorld.getName());
        assertEquals(9, myWorld.getWeightClassListSize());
        for (
                WeightClass weightClass : myWorld.getWeightClassList()) {
            testReadValidWeightClass(weightClass);
        }
    }

    // EFFECTS: helper that checks if the weightClass is read properly
    private void testReadValidWeightClass(WeightClass weightClass) {
        assertTrue(weightClass.getWeightClassCode() >= 0
                && weightClass.getWeightClassCode() <= 8);

        assertEquals(5, weightClass.getFightersSize());
        for (Fighter fighter : weightClass.getFighters()) {
            testReadValidFighter(fighter);
        }
        assertTrue(weightClass.getMatchHistorySize() >= 0 &&
                weightClass.getMatchHistorySize() <= 1);
        for (Fight fight : weightClass.getMatchHistory()) {
            testReadValidFight(fight);
        }
    }

    // EFFECTS: helper that checks if the fight is read properly
    private void testReadValidFight(Fight fight) {
        testReadValidFighter(fight.getWinner());
        testReadValidFighter(fight.getLoser());
        assertEquals("Joss Haggerty beat Josh Holcomb by knockout!", fight.getSummary());
        assertEquals("Josh vs Joss", fight.getFightName());
    }

    // EFFECTS: helper that checks if the fighter is read properly
    private void testReadValidFighter(Fighter fighter) {
        assertNotEquals("", fighter.getName());
        assertTrue(fighter.getStance().equals("southpaw")
                || fighter.getStance().equals("orthodox"));
        assertTrue(fighter.getWeight() >= 0);
        assertTrue(fighter.getHeight() > 1 && fighter.getHeight() < 107);
        assertTrue(fighter.getReach() > 1 &&fighter.getHeight() < 107);
        assertTrue(fighter.getAge() >= 21 && fighter.getAge() <= 34);
        assertTrue(fighter.getWins() >= 0);
        assertTrue(fighter.getLosses() >= 0);
    }
}
