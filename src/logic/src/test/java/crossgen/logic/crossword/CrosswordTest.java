package crossgen.logic.crossword;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import crossgen.logic.grid.Grid;
import crossgen.logic.grid.Direction;

import org.junit.Test;

public class CrosswordTest
{
    @Test
    public void testGrid() {
        Grid grid = new CanadianCrossword(10, 20);
        assertEquals(10, grid.getWidth());
        assertEquals(20, grid.getHeight());
    }

    @Test
    public void testClues() {
        Clue clue1 = new Clue(0, 0, Direction.HORIZONTAL, "one", "...");
        clue1.setClueNumber(1);
        Clue clue2 = new Clue(0, 0, Direction.VERTICAL, "oak", "...");
        clue1.setClueNumber(1);

        assertEquals(1, clue1.getClueNumber());
        assertEquals(3, clue1.getWordLength());
        assertTrue(clue1.isOrthogonal(clue2));
    }
}
