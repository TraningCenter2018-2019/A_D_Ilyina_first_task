package netcracker.practice.crossgen.logic.grid;

public interface Word {

    Angle getAngle();
    int getCol();
    int getRow();
    Direction getDirection();
    int getLength();
    boolean isOrthogonal(Word word);

}
