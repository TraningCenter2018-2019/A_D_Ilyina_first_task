package netcracker.practice.crossgen.logic.angle;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AnglePickerTest {
    @Test
    public void testAnglePicker() {
        AnglePicker anglePicker = new AnglePicker();
        Angle straight = anglePicker.getAngle("straight");
        Angle diagonal = anglePicker.getAngle("diagonal");

        assertTrue(straight instanceof StraightAngle);
        assertTrue(diagonal instanceof DiagonalAngle);
    }
}
