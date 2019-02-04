package netcracker.practice.crossgen.logic.angle;

import netcracker.practice.crossgen.logic.angle.Angle;
import netcracker.practice.crossgen.logic.angle.DiagonalAngle;
import netcracker.practice.crossgen.logic.grid.BaseGrid;
import netcracker.practice.crossgen.logic.word.Word;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class DiagonalAngleTest {

    @Ignore
    @Test
    public void testDiagonalAngle() {
        BaseGrid grid = new BaseGrid(4, 5);
        Angle angle = new DiagonalAngle();
        List<Word> gridWords = angle.findGridWords(grid);

        assertEquals(12, gridWords.size());
    }

}
