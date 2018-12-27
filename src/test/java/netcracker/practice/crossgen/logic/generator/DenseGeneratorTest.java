package netcracker.practice.crossgen.logic.generator;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;
import netcracker.practice.crossgen.logic.grid.Angle;
import netcracker.practice.crossgen.logic.grid.AnglePicker;
import netcracker.practice.crossgen.logic.grid.StraightAngleTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.logging.Logger;

public class DenseGeneratorTest {

    private final Logger log = Logger.getLogger(StraightAngleTest.class.getName());

    CanadianCrossword cross;
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

        CanadianCrossword cross = new CanadianCrossword(9, 9);
        Angle angle = new AnglePicker().getAngle("straight");
        gen = new CanadianCrosswordGenerator(angle, cross, clues);
    }

    @Before
    public void setStartTime() {
        startTime = System.currentTimeMillis();
    }

    @After
    public void cleanup() {
        gen = null;
        cross = null;
    }

    @After
    public void showTimeElapsed() {
        log.info("Time elapsed: " + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void testSingleGeneration() {
        CanadianCrossword genCrossword = (CanadianCrossword) gen.generate();

        if (genCrossword == null)
            log.info("Solution not found");
        else {
            log.info("Generated crossword:\n" + genCrossword.stringify());
            log.info("Clues count: " + genCrossword.cluesCount());
        }
    }

    @Test
    public void testSeveralGenerations() {
        CanadianCrossword genCrossword = (CanadianCrossword) gen.generate();
        int bestCount = 0;

        for (int i = 0; i < 10; i++) {
            genCrossword = (CanadianCrossword) gen.generate();
            if (genCrossword == null)
                log.info("Solution not found");
            else {
                int cluesCount = genCrossword.cluesCount();
                log.info("Clues count: " + cluesCount);
                if (cluesCount > bestCount) bestCount = cluesCount;
            }
        }

        log.info("Best count: " + bestCount);
    }

}
