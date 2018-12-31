package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.logic.word.Direction;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DirectionTest {

    @Test
    public void testOrthogonal() {
        Direction hor = Direction.HORIZONTAL;
        Direction ver = Direction.VERTICAL;
        Direction diag = Direction.DIAGONAL;
        Direction anti = Direction.ANTI_DIAGONAL;

        assertFalse(hor.isOrthogonal(hor));
        assertFalse(ver.isOrthogonal(ver));
        assertFalse(diag.isOrthogonal(diag));
        assertFalse(anti.isOrthogonal(anti));

        assertFalse(hor.isOrthogonal(diag));
        assertFalse(hor.isOrthogonal(anti));
        assertFalse(ver.isOrthogonal(diag));
        assertFalse(ver.isOrthogonal(anti));

        assertFalse(diag.isOrthogonal(hor));
        assertFalse(diag.isOrthogonal(ver));
        assertFalse(anti.isOrthogonal(hor));
        assertFalse(anti.isOrthogonal(ver));

        assertTrue(hor.isOrthogonal(ver));
        assertTrue(ver.isOrthogonal(hor));

        assertTrue(diag.isOrthogonal(anti));
        assertTrue(anti.isOrthogonal(diag));
    }

}
