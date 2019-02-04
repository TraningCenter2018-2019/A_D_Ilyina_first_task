package netcracker.practice.crossgen.logic.crossword;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import netcracker.practice.crossgen.Settings;
import netcracker.practice.crossgen.logic.grid.Coordinate;
import netcracker.practice.crossgen.logic.word.Direction;

import netcracker.practice.crossgen.logic.word.Clue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CanadianCrosswordTest
{
    CanadianCrossword emptyCross;
    CanadianCrossword cross;

    @Before
    public void setUp() {
        emptyCross = new CanadianCrossword();

        HashSet<Clue> clues = new HashSet<>();
        clues.add(new Clue(0, 0, Direction.HORIZONTAL, "aaa", "..."));
        clues.add(new Clue(0, 0, Direction.VERTICAL, "ab", "..."));
        clues.add(new Clue(0, 2, Direction.VERTICAL, "ac", "..."));
        cross = new CanadianCrossword(clues);
    }

    @After
    public void cleanUp() {
        emptyCross = null;
        cross = null;
    }

    @Test
    public void testClues() {
        assertEquals(0, emptyCross.cluesCount());
        assertEquals(3, cross.cluesCount());
    }

    @Test
    public void getSortedClues() {
        Map<String, List<String>> sortedClues = cross.getSortedClues();
        assertEquals(2, sortedClues.size());

        assertEquals(1, sortedClues.get(Direction.HORIZONTAL.getName()).size());
        assertEquals(2, sortedClues.get(Direction.VERTICAL.getName()).size());
    }

    @Test
    public void getConstraints() {
        HashSet<Coordinate> constraints = new HashSet<>();
        constraints.add(new Coordinate(1, 1));
        assertEquals(constraints, cross.getConstraints());
        assertEquals(new HashSet<>(), emptyCross.getConstraints());
    }

    @Test
    public void getHeight() {
        assertEquals(0, emptyCross.getHeight());
        assertEquals(2, cross.getHeight());
    }

    @Test
    public void getWidth() {
        assertEquals(0, emptyCross.getWidth());
        assertEquals(3, cross.getWidth());
    }

    @Test
    public void getSymbol() {
        assertEquals('a', cross.getSymbol(0, 0));
        assertEquals(Settings.CONSTRAINED_SYMBOL, cross.getSymbol(1, 1));
    }

    @Test(expected=IllegalArgumentException.class)
    public void getSymbolInEmptyCrossword() {
        emptyCross.getSymbol(0, 0);
    }

    @Test
    public void toCharMatrix() {
        char[][] matrix = {{'a', 'a', 'a'},
                           {'b', Settings.CONSTRAINED_SYMBOL, 'c'}};
        assertTrue(Arrays.deepEquals(matrix, cross.toCharMatrix()));
    }

    @Test
    public void testToString() {
        assertEquals("", emptyCross.toString());
        assertEquals("aaa\r\nb⬛c\r\n", cross.toString());
    }
}
