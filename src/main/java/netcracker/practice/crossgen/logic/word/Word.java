package netcracker.practice.crossgen.logic.word;

import netcracker.practice.crossgen.logic.angle.Angle;

public interface Word {

    Angle getAngle();
    int getCol();
    int getRow();
    int getEndCol();
    int getEndRow();
    Direction getDirection();
    int getLength();
    String getString();
    boolean isOrthogonal(Word word);

}
