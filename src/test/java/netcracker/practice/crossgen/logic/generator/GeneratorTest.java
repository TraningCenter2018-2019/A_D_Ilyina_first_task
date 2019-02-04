package netcracker.practice.crossgen.logic.generator;

import netcracker.practice.crossgen.Settings;
import netcracker.practice.crossgen.logic.angle.Angle;
import netcracker.practice.crossgen.logic.angle.AnglePicker;
import netcracker.practice.crossgen.logic.angle.StraightAngleTest;
import netcracker.practice.crossgen.logic.grid.BaseGrid;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;

public class GeneratorTest {

    private final Logger log = LoggerFactory.getLogger(StraightAngleTest.class.getName());

    CanadianCrosswordGenerator gen;
    long startTime;

    @Before
    public void setGen() {
        BaseGrid grid = new BaseGrid(4, 4);
        Angle angle = new AnglePicker().getAngle("straight");
        HashMap<String, String> clues = new HashMap<>();
        clues.put("кета", "...");
        clues.put("баян", "...");
        clues.put("кот", "...");
        clues.put("як", "...");
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
    public void testSingleGenerateSolution() {
        Solution solution = gen.generateSolution();
        log.info("Solution:\n" + solution.toString());
    }

    @Test
    @Ignore("Test takes 10s to run")
    public void testManyGenerateSolution() {
        for (int i = 0; i < 10; i++) {
            Solution solution = gen.generateSolution();
            log.info("Solution:\n" + solution.toString());
        }
    }

    @Test
    public void testMaxTimeGenerateSolution() {
        int counter = 0;
        while (System.currentTimeMillis() - startTime < Settings.GENERATION_TIMEOUT) {
            Solution solution = gen.generateSolution();
            counter++;
        }
        log.info("Number of generated solutions: " + counter);
    }

    @Test
    public void testGenerate() {
        CanadianCrossword genCrossword = gen.generate();
        if (genCrossword == null)
            log.info("Solution not found");
        else
            log.info("Generated crossword:\n" + genCrossword.toString());
    }

}
