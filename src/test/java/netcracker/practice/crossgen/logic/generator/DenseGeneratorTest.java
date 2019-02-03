package netcracker.practice.crossgen.logic.generator;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;
import netcracker.practice.crossgen.logic.angle.Angle;
import netcracker.practice.crossgen.logic.angle.AnglePicker;
import netcracker.practice.crossgen.logic.grid.BaseGrid;
import netcracker.practice.crossgen.logic.grid.Coordinate;
import netcracker.practice.crossgen.logic.angle.StraightAngleTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.*;
import java.util.logging.Logger;

public class DenseGeneratorTest {

    private final Logger log = Logger.getLogger(StraightAngleTest.class.getName());

    CanadianCrosswordGenerator gen;
    long startTime;

    @Before
    public void setGen() {
        HashMap<String, String> clues = new HashMap<>();
        clues.put("лис", "...");
        clues.put("ода", "...");
        clues.put("усы", "...");
        clues.put("икра", "...");
        clues.put("тротил", "...");
        clues.put("рада", "...");
        clues.put("зевс", "...");
        clues.put("щи", "...");
        clues.put("сатира", "...");
        clues.put("сани", "...");
        clues.put("трак", "...");
        clues.put("агат", "...");
        clues.put("мао", "...");
        clues.put("ура", "...");
        clues.put("ока", "...");
        clues.put("воз", "...");
        clues.put("щит", "...");
        clues.put("дети", "...");
        clues.put("рак", "...");
        clues.put("лавр", "...");
        clues.put("сага", "...");
        clues.put("собака", "...");
        clues.put("кризис", "...");
        clues.put("урал", "...");
        clues.put("рама", "...");
        clues.put("сад", "...");
        clues.put("гана", "...");
        clues.put("али", "...");
        clues.put("ион", "...");
        clues.put("си", "...");

        HashSet<Coordinate> constraints = new HashSet<>();
        constraints.add(new Coordinate(0, 1));
        constraints.add(new Coordinate(0, 5));
        constraints.add(new Coordinate(1, 4));
        constraints.add(new Coordinate(1, 8));
        constraints.add(new Coordinate(2, 5));
        constraints.add(new Coordinate(3, 0));
        constraints.add(new Coordinate(3, 7));
        constraints.add(new Coordinate(4, 2));
        constraints.add(new Coordinate(4, 4));
        constraints.add(new Coordinate(4, 6));
        constraints.add(new Coordinate(5, 1));
        constraints.add(new Coordinate(5, 8));
        constraints.add(new Coordinate(6, 4));
        constraints.add(new Coordinate(7, 0));
        constraints.add(new Coordinate(7, 5));
        constraints.add(new Coordinate(8, 3));
        constraints.add(new Coordinate(8, 7));

        BaseGrid grid = new BaseGrid(9, 9, constraints);
        Angle angle = new AnglePicker().getAngle("straight");
        gen = new CanadianCrosswordGenerator(angle, grid, clues);
    }

    @Before
    public void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    @After
    public void cleanup() {
        gen = null;
    }

    @After
    public void showTimeElapsed() {
        log.info("Time elapsed: " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testSingleGeneration() {
        CanadianCrossword genCrossword = gen.generate();

        if (genCrossword == null)
            log.info("Solution not found");
        else {
            log.info("Generated crossword:\n" + genCrossword.toString());
            log.info("Clues count: " + genCrossword.cluesCount());
        }
    }

    @Test
    @Ignore("Test takes 10s to run")
    public void testSeveralGenerations() {
        int bestCount = 0;

        for (int i = 0; i < 10; i++) {
            CanadianCrossword genCrossword = gen.generate();
            if (genCrossword == null)
                log.info("Solution not found");
            else {
                int cluesCount = genCrossword.cluesCount();
                log.info("Clues count: " + cluesCount);
                assertTrue(bestCount >= cluesCount);
                if (cluesCount > bestCount) bestCount = cluesCount;
            }
        }

        log.info("Best count: " + bestCount);
    }

}
