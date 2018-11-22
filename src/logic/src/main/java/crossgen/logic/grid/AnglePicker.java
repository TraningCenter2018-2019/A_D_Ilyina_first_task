package crossgen.logic.grid;

public class AnglePicker {
    public Angle getAngle(String angleType) {
        if (angleType == null)
            return null;
        if(angleType.equalsIgnoreCase("straight")){
            return new Straight();

        } else if(angleType.equalsIgnoreCase("diagonal")) {
            return new Diagonal();
        }
        return null;
    }
}
