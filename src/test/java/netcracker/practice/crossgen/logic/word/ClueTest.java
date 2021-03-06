package netcracker.practice.crossgen.logic.word;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClueTest
{
    @Test
    public void testClues() {
        Clue clue1 = new Clue(0, 0, Direction.HORIZONTAL, "one", "...");
        clue1.setClueNumber(1);
        Clue clue2 = new Clue(0, 0, Direction.VERTICAL, "oak", "...");
        clue2.setClueNumber(1);

        assertEquals(1, clue1.getClueNumber());
        assertEquals(1, clue2.getClueNumber());

        assertTrue(clue1.isOrthogonal(clue2));
    }
}
