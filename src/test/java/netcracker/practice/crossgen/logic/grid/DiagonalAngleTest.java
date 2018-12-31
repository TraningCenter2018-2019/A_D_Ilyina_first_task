package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.logic.angle.Angle;
import netcracker.practice.crossgen.logic.angle.DiagonalAngle;
import netcracker.practice.crossgen.logic.word.Word;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import netcracker.practice.crossgen.logic.crossword.CanadianCrossword;

import java.util.List;

public class DiagonalAngleTest {

    @Test
    public void testDiagonalAngle() {
        Grid grid = new CanadianCrossword(4, 5);
        Angle angle = new DiagonalAngle();
        List<Word> gridWords = angle.findGridWords(grid);

        assertEquals(12, gridWords.size());
    }

}
