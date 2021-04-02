package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UfcWorldTest {
    UfcWorld myWorld;
    UfcWorld emptyWorld;

    @BeforeEach
    public void runBefore() {
        myWorld = new UfcWorld("my world", 2);
        emptyWorld = new UfcWorld("empty world", 1);
    }

    @Test
    public void constructionFillUpTest() {
        assertEquals(9, emptyWorld.getWeightClassListSize());
        for (int i = 0; i < 9; i++) {
            assertNotNull(myWorld.getWeightClassByCode(i));
            assertNotNull(myWorld.getWeightClassByCode(i).getFighters());
        }
        assertEquals("my world", myWorld.getName());
        assertEquals(5, myWorld.getNumberOfNames());
    }

    @Test
    public void constructionDoNotFillUpTest() {
        assertEquals("empty world", emptyWorld.getName());
        assertEquals(9, emptyWorld.getWeightClassListSize());
    }

    @Test
    public void getWeightClassByCodeValid() {
        assertNotNull(myWorld.getWeightClassByCode(1));
    }

    @Test
    public void getWeightClassByCodeIsNotValid() {
        assertNull(emptyWorld.getWeightClassByCode(10));
    }

    @Test
    public void addWeightClassTest() {
        WeightClass weightClass = new WeightClass(15, 0, 1000);
        emptyWorld.addWeightClass(weightClass);
        assertEquals(weightClass, emptyWorld.getWeightClassByCode(15));
    }
}
