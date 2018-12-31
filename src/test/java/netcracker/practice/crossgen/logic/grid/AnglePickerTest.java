package netcracker.practice.crossgen.logic.grid;

import netcracker.practice.crossgen.logic.angle.Angle;
import netcracker.practice.crossgen.logic.angle.AnglePicker;
import netcracker.practice.crossgen.logic.angle.DiagonalAngle;
import netcracker.practice.crossgen.logic.angle.StraightAngle;
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
