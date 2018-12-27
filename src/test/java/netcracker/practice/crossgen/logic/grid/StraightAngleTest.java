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
    public void testPlaceStringInGrid() {
        Grid cross = new CanadianCrossword(5, 5);
        Grid grid = new CharGrid(cross);
        Word word = new GridWord(2, 4, Direction.VERTICAL, 2);
        String wordString = "no";
        Angle angle = new StraightAngle();

        angle.placeStringInGrid(wordString, word, grid);
        log.info("Grid after placement:\n" + grid.toString());

        assertEquals('n', grid.getSymbol(2, 4));
        assertEquals('o', grid.getSymbol(3, 4));
    }

    @Test
    public void testPlaceWordInGrid() {
        Grid cross = new CanadianCrossword(5, 5);
        Grid grid = new CharGrid(cross);
        Word word = new GridWord(1, 1, Direction.HORIZONTAL, 3);
        String wordString = "yes";
        Angle angle = new StraightAngle();

        angle.placeWordInGrid(wordString, word, grid);
        log.info("Grid after placement:\n" + grid.toString());

        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(1, 0));
        assertEquals('y', grid.getSymbol(1, 1));
        assertEquals('e', grid.getSymbol(1, 2));
        assertEquals('s', grid.getSymbol(1, 3));
        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(1, 4));
    }

    @Test
    public void testSetProhibitedBorders() {
        Grid cross = new CanadianCrossword(5, 5);
        Grid grid = new CharGrid(cross);
        Angle angle = new StraightAngle();
        log.info("Initial grid:\n" + grid.toString());

        Word word1 = new GridWord(4, 2, Direction.HORIZONTAL, 3);
        angle.setProhibitedBorders(word1, grid);
        log.info("Current grid:\n" + grid.toString());
        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(4, 1));

        Word word2 = new GridWord(1, 0, Direction.VERTICAL, 3);
        angle.setProhibitedBorders(word2, grid);
        log.info("Current grid:\n" + grid.toString());
        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(0, 0));
        assertEquals(Settings.PROHIBITED_SYMBOL, grid.getSymbol(4, 0));
    }

    @Test
    public void testWordConflictsGrid() {
        Grid cross = new CanadianCrossword(3, 3);
        Grid grid = new CharGrid(cross);
        grid.setSymbol(0, 0, Settings.PROHIBITED_SYMBOL);
        grid.setSymbol(0, 2, Settings.PROHIBITED_SYMBOL);
        grid.setSymbol(2, 0, Settings.PROHIBITED_SYMBOL);
        grid.setSymbol(2, 2, Settings.PROHIBITED_SYMBOL);

        log.info("Current grid:\n" + grid.toString());

        Angle angle = new StraightAngle();

        Word word1 = new GridWord(0, 1, Direction.HORIZONTAL, 2);
        String wordString1 = "aa";
        assertTrue(angle.wordConflictsGrid(wordString1, word1, grid));

        Word word2 = new GridWord(0, 1, Direction.VERTICAL, 3);
        String wordString2 = "aaa";
        assertFalse(angle.wordConflictsGrid(wordString2, word2, grid));

        grid.setSymbol(2, 1, 'a');
        log.info("Current grid:\n" + grid.toString());
        assertFalse(angle.wordConflictsGrid(wordString2, word2, grid));

        grid.setSymbol(0, 1, 'b');
        log.info("Current grid:\n" + grid.toString());
        assertTrue(angle.wordConflictsGrid(wordString2, word2, grid));
    }

}
