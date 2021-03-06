package netcracker.practice.crossgen.logic.angle;

import netcracker.practice.crossgen.Settings;
import netcracker.practice.crossgen.logic.grid.BaseGrid;
import netcracker.practice.crossgen.logic.grid.CharGrid;
import netcracker.practice.crossgen.logic.grid.Coordinate;
import netcracker.practice.crossgen.logic.grid.MutableGrid;
import netcracker.practice.crossgen.logic.word.Clue;
import netcracker.practice.crossgen.logic.word.Direction;
import netcracker.practice.crossgen.logic.word.GridWord;
import netcracker.practice.crossgen.logic.word.Word;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.*;

public class StraightAngleTest {

    private final Logger log = LoggerFactory.getLogger(StraightAngleTest.class.getName());
    @Test
    public void testGetCoordinates() {
        Angle angle = new StraightAngle();
        Word word = new GridWord(3, 2, Direction.VERTICAL, 4);

        HashSet<Coordinate> coordinates = new HashSet<>();
        coordinates.add(new Coordinate(3, 2));
        coordinates.add(new Coordinate(4, 2));
        coordinates.add(new Coordinate(5, 2));
        coordinates.add(new Coordinate(6, 2));

        Set<Coordinate> acquiredCoordinates = angle.getCoordinates(word);
        assertTrue(acquiredCoordinates.equals(coordinates));
    }

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
    public void testGetRandomWord() {
        BaseGrid grid = new BaseGrid(4, 4);
        Angle angle = new StraightAngle();

        Word randomWord1 = angle.getRandomWord(grid, 4);
        log.info("Result 1: " + randomWord1.toString());

        Word randomWord2 = angle.getRandomWord(grid, 2);
        log.info("Result 2: " + randomWord2.toString());
    }

    @Test
    public void testFindGridWords() {
        BaseGrid grid = new BaseGrid(4, 5);
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
        BaseGrid grid = new BaseGrid(3, 2);
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
    public void testPutWordInGrid() {
        BaseGrid baseGrid = new BaseGrid(5, 5);
        MutableGrid grid = new CharGrid(baseGrid);
        Word word = new Clue(1, 1, Direction.HORIZONTAL, "yes", null);
        Angle angle = new StraightAngle();

        angle.putWordInGrid(word, grid);
        log.info("Grid after placement:\n" + grid.toString());

        assertEquals('y', grid.getSymbol(1, 1));
        assertEquals('e', grid.getSymbol(1, 2));
        assertEquals('s', grid.getSymbol(1, 3));
    }

    @Test
    public void testSetProhibitedBorders() {
        BaseGrid baseGrid = new BaseGrid(5, 5);
        MutableGrid grid = new CharGrid(baseGrid);
        Angle angle = new StraightAngle();
        log.info("Initial grid:\n" + grid.toString());

        Word word1 = new GridWord(4, 2, Direction.HORIZONTAL, 3);
        angle.setConstrainedBorders(word1, grid);
        log.info("Current grid:\n" + grid.toString());
        assertEquals(Settings.CONSTRAINED_SYMBOL, grid.getSymbol(4, 1));

        Word word2 = new GridWord(1, 0, Direction.VERTICAL, 3);
        angle.setConstrainedBorders(word2, grid);
        log.info("Current grid:\n" + grid.toString());
        assertEquals(Settings.CONSTRAINED_SYMBOL, grid.getSymbol(0, 0));
        assertEquals(Settings.CONSTRAINED_SYMBOL, grid.getSymbol(4, 0));
    }

    @Test
    public void testWordConflictsGrid() {
        BaseGrid baseGrid = new BaseGrid(3, 3);
        MutableGrid grid = new CharGrid(baseGrid);
        grid.setSymbol(0, 0, Settings.CONSTRAINED_SYMBOL);
        grid.setSymbol(0, 2, Settings.CONSTRAINED_SYMBOL);
        grid.setSymbol(2, 0, Settings.CONSTRAINED_SYMBOL);
        grid.setSymbol(2, 2, Settings.CONSTRAINED_SYMBOL);

        log.info("Current grid:\n" + grid.toString());

        Angle angle = new StraightAngle();

        Word word1 = new Clue(0, 1, Direction.HORIZONTAL, "aa", null);
        assertTrue(angle.wordConflictsGrid(word1, grid));

        Word word2 = new Clue(0, 1, Direction.VERTICAL, "aaa", null);
        assertFalse(angle.wordConflictsGrid(word2, grid));

        grid.setSymbol(2, 1, 'a');
        log.info("Current grid:\n" + grid.toString());
        assertFalse(angle.wordConflictsGrid(word2, grid));

        grid.setSymbol(0, 1, 'b');
        log.info("Current grid:\n" + grid.toString());
        assertTrue(angle.wordConflictsGrid(word2, grid));
    }

}
