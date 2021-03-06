package persistence;

import model.Fight;
import model.Fighter;
import model.UfcWorld;
import model.WeightClass;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static junit.framework.Assert.*;

// Tests inspired by JsonSerializationDemo tests
public class JsonWriterTest {

    @Test
    public void writerFileNotFoundTest() {
        try {
            UfcWorld myWorld = new UfcWorld("ufc world", false);
            JsonWriter writer = new JsonWriter("./data/invalidFileName\0:bad.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            // code is working
        }
    }

    @Test
    public void writerEmptyUfcWorldTest() {
        try {
            UfcWorld myWorld = new UfcWorld("my world", false);
            JsonWriter writer = new JsonWriter("./data/emptyUfCWorldCreatedTest.json");
            writer.open();
            writer.write(myWorld);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyUfcWorldCreatedTest.json");
            myWorld = reader.read();
            assertEquals("my world", myWorld.getName());
            assertEquals(0, myWorld.getWeightClassListSize());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void ufcWorldWrittenTest() {
        try {
            UfcWorld myWorld = new UfcWorld("partial ufc world", false);
            Fighter fighter1 = new Fighter("Ben Vinnick",
                    "southpaw",
                    145.0, 69, 21, 70);
            Fighter fighter4 = new Fighter("Small Man Cam",
                    "orthodox",
                    150.0, 73, 27, 75);
            WeightClass weightClass = new WeightClass(20, 100, 200);
            weightClass.addFighter(fighter1);
            weightClass.addFighter(fighter4);
            Fight fight = new Fight(fighter1, fighter4, "Ben vs Cam", " By knockout!");
            weightClass.addFight(fight);
            myWorld.addWeightClass(weightClass);

            JsonWriter writer = new JsonWriter("./data/worldWrittenTest.json");
            writer.open();
            writer.write(myWorld);
            writer.close();

            JsonReader reader = new JsonReader("./data/worldWrittenTest.json");
            myWorld = reader.read();
            assertEquals("partial ufc world", myWorld.getName());
            assertEquals(1, myWorld.getWeightClassListSize());
            assertNotNull(myWorld.getWeightClassByCode(20));
            assertEquals(2, weightClass.getFightersSize());
            assertEquals(1, weightClass.getMatchHistorySize());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}
