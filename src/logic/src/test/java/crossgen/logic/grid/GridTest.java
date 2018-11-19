package crossgen.logic.grid;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import crossgen.logic.crossword.CanadianCrossword;

import java.util.List;

public class GridTest {
    @Test
    public void testStraightAngle() {
        Grid grid = new CanadianCrossword(4, 5);
        Angle angle = new Straight();
        List<Word> gridWords = angle.findGridWords(grid);

        assertEquals(9, gridWords.size());
        assertEquals(4, gridWords.get(0).getWordLength());
        assertEquals(5, gridWords.get(5).getWordLength());
    }

    @Test
    public void testDiagonalAngle() {
        Grid grid = new CanadianCrossword(4, 5);
        Angle angle = new Diagonal();
        List<Word> gridWords = angle.findGridWords(grid);

        assertEquals(12, gridWords.size());
    }
}
