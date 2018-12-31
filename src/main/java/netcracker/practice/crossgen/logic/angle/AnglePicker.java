package netcracker.practice.crossgen.logic.angle;

public class AnglePicker {

    public Angle getAngle(String angleType) {
        if (angleType == null)
            return null;
        if(angleType.equalsIgnoreCase("straight")){
            return new StraightAngle();

        } else if(angleType.equalsIgnoreCase("diagonal")) {
            return new DiagonalAngle();
        }
        return null;
    }

}
