package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NamesTest {
    Names names;

    @BeforeEach
    public void runBefore() {
        names = new Names();
    }

    @Test
    public void constructorTest() {
        assertTrue(names.getFirstNamesList().size() > 0);
        assertTrue(names.getLastNamesList().size() > 0);
    }

    @Test
    public void getSomeFirstNamesTest() {
        int size = names.getFirstNamesList().size();
        List<String> lastNames = names.getSomeLastNames(5);
        assertEquals(5, names.getSomeFirstNames(5).size());
    }

    @Test
    public void getSomeLastNamesTest() {
        int size = names.getLastNamesList().size();
        List<String> lastNames = names.getSomeLastNames(5);
        assertEquals(5, lastNames.size());
        String previousName = "";
        for (String name : lastNames) {
            assertNotEquals(name, previousName);
            }
    }

    @Test
    public void createNamesTest() {
        List<String> fullNames = names.createNames(5);
        assertEquals(5, fullNames.size());
        for (String fullName : fullNames) {
            assertTrue(fullName.contains(" "));
        }
    }
}
