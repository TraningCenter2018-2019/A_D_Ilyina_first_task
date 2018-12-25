package netcracker.practice.crossgen.logic.grid;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;

import java.util.List;
import java.util.Map;

public class DiagonalAngleTest {

    @Test
    public void testDiagonalAngle() {
        Grid grid = new CanadianCrossword(4, 5);
        Angle angle = new DiagonalAngle();
        List<Word> gridWords = angle.findGridWords(grid);

        assertEquals(12, gridWords.size());
    }

    @Test
    public void testDiagonalIntersections() {
        Grid grid = new CanadianCrossword(4, 5);

        Angle angle = new DiagonalAngle();
        Map<Word, Map<Word, Integer>> intersections = angle.findGridWordIntersections(grid);

        assertEquals(12, intersections.size());
    }
}
