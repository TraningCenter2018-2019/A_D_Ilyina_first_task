package netcracker.practice.crossgen.logic.generator;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.*;

import netcracker.practice.crossgen.logic.grid.*;
import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;

public class GeneratorTest {
    @Test
    public void testMatchEquality() {
        Match wordMatch1 = new WordMatch("one", "two", 0, 2);
        Match wordMatch2 = new WordMatch("one", "two", 0, 2);
        Match wordMatch3 = new WordMatch("two", "one", 2, 0);

        assertEquals(wordMatch1, wordMatch1);
        assertEquals(wordMatch1, wordMatch2);
        assertEquals(wordMatch1, wordMatch3);

        int hash1 = wordMatch1.hashCode();
        int hash2 = wordMatch2.hashCode();
        int hash3 = wordMatch3.hashCode();

        assertEquals(hash1, hash2);
        assertEquals(hash1, hash3);
    }

    /*
    @Test
    public void testCanadianCrosswordGenerator() {
        Grid grid = new CanadianCrossword(4, 4);
        Angle angle = new AnglePicker().getAngle("straight");
        HashMap<String, String> clues = new HashMap<>();
        clues.put("кета", "...");
        clues.put("баян", "...");
        clues.put("кот", "...");
        clues.put("як", "...");

        CanadianCrosswordGenerator gen =
                new CanadianCrosswordGenerator(angle, grid, clues);
        gen.generate();
    }
    */
}
