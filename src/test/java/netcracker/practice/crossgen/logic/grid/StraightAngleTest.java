package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.Settings;
import org.junit.Test;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class StraightAngleTest {

    private final Logger log = Logger.getLogger(StraightAngleTest.class.getName());

    @Test
    public void testGetIntersectingWordCol() {
        Angle angle = new StraightAngle();
        int position1 = 1;
        int position2 = 2;

        Word intersectedWord = new GridWord(1, 2, Direction.VERTICAL, 3);
        int col = angle.getIntersectingWordCol(intersectedWord, position1, position2);
        assertEquals(0, col);

        intersectedWord = new GridWord(2, 0, Direction.HORIZONTAL, 3);
        col = angle.getIntersectingWordCol(intersectedWord, position1, position2);
        assertEquals(1, col);
    }

    @Test
    public void testGetIntersectingWordRow() {
        Angle angle = new StraightAngle();
        int position1 = 1;
        int position2 = 2;

        Word intersectedWord = new GridWord(1, 2, Direction.VERTICAL, 3);
        int row = angle.getIntersectingWordRow(intersectedWord, position1, position2);
        assertEquals(2, row);

        intersectedWord = new GridWord(2, 0, Direction.HORIZONTAL, 3);
        row = angle.getIntersectingWordRow(intersectedWord, position1, position2);
        assertEquals(0, row);
    }

    @Test
    public void getRandomWord() {
        Grid grid = new CanadianCrossword(4, 4);
        Angle angle = new StraightAngle();

        Word randomWord1 = angle.getRandomWord(grid, 4);
        log.info("Result 1: " + randomWord1.toString());

        Word randomWord2 = angle.getRandomWord(grid, 2);
        log.info("Result 2: " + randomWord2.toString());
    }

    @Test
    public void testFindGridWords() {
        Grid grid = new CanadianCrossword(4, 5);
        Angle angle = new StraightAngle();
        List<Word> gridWords = angle.findGridWords(grid);

        assertEquals(9, gridWords.size());
        assertEquals(5, gridWords.get(0).getLength());
        assertEquals(4, gridWords.get(5).getLength());

        log.info("Result: " + gridWords.toString());
    }

    @Test
    public void testFitsWithinBounds() {
        Angle angle = new StraightAngle();
        Grid grid = new CanadianCrossword(3, 2);
        Word word1 = new GridWord(0, 2, Direction.VERTICAL, 3);
        Word word2 = new GridWord(2, 1, Direction.VERTICAL, 3);
        Word word3 = new GridWord(0, 0, Direction.HORIZONTAL, 2);
        Word word4 = new GridWord(0, 0, Direction.HORIZONTAL, 4);

        assertTrue(angle.fitsWithinBounds(word1, grid));
        assertTrue(angle.fitsWithinBounds(word3, grid));
        assertFalse(angle.fitsWithinBounds(word2, grid));
        assertFalse(angle.fitsWithinBounds(word4, grid));
    }

    @Test
    public void testWordConflictsGrid() {
        Grid grid = new CanadianCrossword(3, 3);
        grid.setSymbol(0, 0, Settings.PROHIBITED_SYMBOL);
        grid.setSymbol(0, 2, Settings.PROHIBITED_SYMBOL);
        grid.setSymbol(2, 0, Settings.PROHIBITED_SYMBOL);
        grid.setSymbol(2, 2, Settings.PROHIBITED_SYMBOL);

        log.fine("Current grid:\n" + grid.toString());

        Angle angle = new StraightAngle();

        Word word1 = new GridWord(0, 1, Direction.HORIZONTAL, 2);
        String wordString1 = "aa";
        assertTrue(angle.wordConflictsGrid(word1, wordString1, grid));

        Word word2 = new GridWord(0, 1, Direction.VERTICAL, 3);
        String wordString2 = "aaa";
        assertFalse(angle.wordConflictsGrid(word2, wordString2, grid));

        grid.setSymbol(2, 1, 'a');
        log.fine("Current grid:\n" + grid.toString());
        assertFalse(angle.wordConflictsGrid(word2, wordString2, grid));

        grid.setSymbol(0, 1, 'b');
        log.fine("Current grid:\n" + grid.toString());
        assertTrue(angle.wordConflictsGrid(word2, wordString2, grid));
    }

    @Test
    public void testPlaceStringInGrid() {
        // TODO : test
    }

    @Test
    public void testPlaceWord() {
        // TODO : test
    }

    @Test
    public void testSetProhibitedBorders() {
        Grid grid = new CanadianCrossword(5, 5);
        Angle angle = new StraightAngle();
        log.fine("Initial grid:\n" + grid.toString());

        Word word1 = new GridWord(4, 2, Direction.HORIZONTAL, 3);
        angle.setProhibitedBorders(word1, grid);
        log.fine("Current grid:\n" + grid.toString());
        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(4, 1));

        Word word2 = new GridWord(1, 0, Direction.VERTICAL, 3);
        angle.setProhibitedBorders(word2, grid);
        log.fine("Current grid:\n" + grid.toString());
        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(0, 0));
        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(4, 0));
    }

    @Test
    public void testWordToString() {
        // TODO : test
    }

}
