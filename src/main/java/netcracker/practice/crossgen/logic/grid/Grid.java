package netcracker.practice.crossgen.logic.grid;

import java.util.Set;

public interface Grid {

    Set<Coordinate> getConstraints();
    int getHeight();
    int getWidth();
    char getSymbol(int row, int col);
    String toString();

}
