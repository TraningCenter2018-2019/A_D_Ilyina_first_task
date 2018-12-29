package netcracker.practice.crossgen.logic.grid;

public interface Word {

    Angle getAngle();
    int getCol();
    int getRow();
    Direction getDirection();
    int getLength();
    String getString();
    boolean isOrthogonal(Word word);

}
