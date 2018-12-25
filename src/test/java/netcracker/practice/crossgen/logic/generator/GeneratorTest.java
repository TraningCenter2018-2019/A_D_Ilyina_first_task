package netcracker.practice.crossgen.logic.generator;

import org.junit.Test;

import java.util.*;

import netcracker.practice.crossgen.logic.grid.*;
import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;

public class GeneratorTest {

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
    }

}
